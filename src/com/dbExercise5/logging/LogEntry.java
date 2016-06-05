package com.dbExercise5.logging;

public class LogEntry
{
    
    private int lsn;
    private int taid;
    private int pageid;
    private String data;
    
    public LogEntry(int lsn, int taid, int pageid, String data) {
	super();
	this.lsn = lsn;
	this.taid = taid;
	this.pageid = pageid;
	this.data = data;
    }
    
    public int getLsn() {
	return lsn;
    }
    public void setLsn(int lsn) {
	this.lsn = lsn;
    }
    
    public int getTaid() {
	return taid;
    }
    public void setTaid(int taid) {
	this.taid = taid;
    }
    
    public int getPageid() {
	return pageid;
    }
    public void setPageid(int pageid) {
	this.pageid = pageid;
    }
    
    public String getData() {
	return data;
    }
    public void setData(String data) {
	this.data = data;
    }
    
    @Override
    public String toString() {
	return lsn + "," + taid + "," + pageid + "," + data;
    }
    
}
