package com.dl.base.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 */
public class Pagination<E extends Serializable> extends BasePage {
	private static final long serialVersionUID = -7323433869689736338L;
	public static ThreadLocal<Pagination> threadLocal = new ThreadLocal<Pagination>();
	private List<E> list;

	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	public Pagination(int pageNo, int pageSize, int totalCount, List<E> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public List<E> getList() {
		return this.list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
}
