package com.dl.base.lock.zk;


import com.dl.base.exception.ServiceException;
import com.dl.base.lock.Callback;
import com.dl.base.lock.DistributedLockTemplate;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ZkDistributedLockTemplate implements DistributedLockTemplate {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ZkDistributedLockTemplate.class);

    private CuratorFramework client;


    public ZkDistributedLockTemplate(CuratorFramework client) {
        this.client = client;
    }


    private boolean tryLock(ZkReentrantLock distributedReentrantLock, Long timeout) throws Exception {
        return distributedReentrantLock.tryLock(timeout, TimeUnit.MILLISECONDS);
    }

    public Object execute(String lockId, int timeout, Callback callback) {
        ZkReentrantLock distributedReentrantLock = null;
        boolean getLock = false;
        try {
            distributedReentrantLock = new ZkReentrantLock(client, lockId);
            if (tryLock(distributedReentrantLock, new Long(timeout))) {
                getLock = true;
                return callback.onGetLock();
            } else {
                return callback.onTimeout();
            }
        } catch (InterruptedException ex) {
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
            throw new ServiceException(-1, ex.getMessage(), ex);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw (ServiceException) e;
            } else {
                throw new ServiceException(-1, e.getMessage(), e);
            }
        } finally {
            if (getLock) {
                distributedReentrantLock.unlock();
            }
        }
    }
}
