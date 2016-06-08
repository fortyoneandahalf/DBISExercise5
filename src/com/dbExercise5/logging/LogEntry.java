package com.dbExercise5.logging;

public class LogEntry
{
    private int lsn;
    private String logType;
    private int taid;
    private int pageid;
    private String data;
    
    public LogEntry(int lsn, String logType, int taid, int pageid, String data) {
	super();
	this.lsn = lsn;
	this.logType = logType;
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
    
    public String getLogType() {
	return logType;
    }

    public void setLogType(String logType) {
	this.logType = logType;
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
    	
    	// If data is blank, make toString output a single space instead of an empty
    	// string. Ensures compatibility with read methods in FileUtilities.
    	String tempData = " ";
    	if (!data.equals("")) {tempData = data;}
    	
    	return lsn + "," + logType + "," + taid + "," + pageid + "," + tempData;
    }
    
}
