package org.training.issuetracker.utils;

public class SearchFilterParams {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private int rows;
	private int page;
	private String sidx;
	private String sord;
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public SearchFilterParams() { }
	
}
