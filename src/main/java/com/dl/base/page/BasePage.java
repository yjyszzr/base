package com.dl.base.page;


import java.io.Serializable;

public abstract class BasePage implements Serializable, IPage {
	private static final long serialVersionUID = -3234802168267319735L;

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	private int pageSize = 10;
	private int totalPage;

	public BasePage(int pageNo, int pageSize, int totalCountt) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCountt;
	}

	private int pageNo = 1;
	private int totalCount = -1;

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	public BasePage() {
	}

	public int getFirstResult() {
		return (this.pageNo - 1) * this.pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = 20;
		} else {
			this.pageSize = pageSize;
		}
	}

	public int getTotalPage() {
		if (this.totalPage <= 0) {
			this.totalPage = (this.totalCount / this.pageSize);
			if ((this.totalPage == 0) || (this.totalCount % this.pageSize != 0)) {
				this.totalPage += 1;
			}
		}
		return this.totalPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public boolean isFirstPage() {
		return this.pageNo <= 1;
	}

	public boolean isLastPage() {
		return this.pageNo >= getTotalPage();
	}

	public int getNextPage() {
		if (isLastPage()) {
			return this.pageNo;
		}
		return this.pageNo + 1;
	}

	public int getCurrentResult() {
		int currentResult = ((getPageNo() - 1) * getPageSize());
		if (currentResult < 0) {
			currentResult = 0;
		}
		return currentResult;
	}

	public int getPrePage() {
		if (isFirstPage()) {
			return this.pageNo;
		}
		return this.pageNo - 1;
	}
}
