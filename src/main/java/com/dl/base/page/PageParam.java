package com.dl.base.page;


import com.dl.base.SerializableEntity;

/**
 * 分页参数
 */
public class PageParam extends SerializableEntity {
	/**
	* 页号
	*/
	private Integer pageNo;
	/**
	 * 单页条数
	 */
	private Integer pageSize;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
