package com.dbExercise5.persistence;

import java.util.Set;

public class Transaction
{
    private int taid;
    private boolean committed;
    private Set<Integer> pages;

    public Transaction(int taid) {
	super();
	this.setTaid(taid);
    }

    public int getTaid() {
	return taid;
    }

    public void setTaid(int taid) {
	this.taid = taid;
    }

    public boolean isCommitted() {
	return committed;
    }

    public void setCommitted(boolean committed) {
	this.committed = committed;
    }

    public Set<Integer> getPages() {
	return pages;
    }

    public void setPages(Set<Integer> pages) {
	this.pages = pages;
    }
    
    public void addPage(int page)
    {
	if (pages.contains(page))
	{
	    pages.remove(page);
	}
	
	pages.add(page);
    }
}
