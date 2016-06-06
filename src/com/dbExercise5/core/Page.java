package com.dbExercise5.core;

public class Page {
	
	private int pageid;
	private int lsn;
    private String data;
    
	public Page(int pageid, int lsn, String data) {
		super();
		this.pageid = pageid;
		this.lsn = lsn;
		this.data = data;
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

	public int getLsn() {
		return lsn;
	}

	public void setLsn(int lsn) {
		this.lsn = lsn;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return pageid + "," + lsn + "," + data;
	}
    
    
}
