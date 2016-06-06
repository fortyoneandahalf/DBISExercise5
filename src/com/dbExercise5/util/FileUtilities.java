package com.dbExercise5.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.dbExercise5.core.Page;
import com.dbExercise5.logging.LogEntry;

public class FileUtilities {
	
	public static final String DATA_FOLDER = "data/";
	public static final String LOG_FOLDER = "log/";
	
	public static String readFromFile(String pathname)
	{
		String data = "";
		try {
			FileReader fr = new FileReader(new File(pathname));
			BufferedReader br = new BufferedReader(fr);
			data = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static LogEntry readLogEntryFromFile(int lsn){
		String logEntryFileName = String.valueOf(lsn);
		LogEntry le = null;
		String leStr = FileUtilities.readFromFile(FileUtilities.LOG_FOLDER+logEntryFileName+".txt");
		String[] leStrArr = leStr.split(",");
		le = new LogEntry(Integer.parseInt(leStrArr[0]), Integer.parseInt(leStrArr[1]), Integer.parseInt(leStrArr[2]), leStrArr[3]);
		return le;
	}
	
	public static Page readPageFromFile(int pageid){
		String PageFileName = String.valueOf(pageid);
		Page p = null;
		String pStr = FileUtilities.readFromFile(FileUtilities.DATA_FOLDER+PageFileName+".txt");
		String[] pStrArr = pStr.split(",");
		p = new Page(Integer.parseInt(pStrArr[0]), Integer.parseInt(pStrArr[1]), pStrArr[2]);
		return p;
	}
	
	public static void writeToFile(String pathname, String data)
	{
		try {
			File f = new File(pathname);
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.write(data);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeLogEntryToFile(LogEntry le)
	{
		writeToFile(FileUtilities.LOG_FOLDER+String.valueOf(le.getLsn())+".txt", le.toString());
	}
	
	public static void writePageToFile(Page p)
	{
		writeToFile(FileUtilities.DATA_FOLDER+String.valueOf(p.getPageid())+".txt", p.toString());
	}
	
	/**
	 * Testing - Yay - It works!
	 * @param a
	 */
//	public static void main(String a[]){
//		FileUtilities.writeLogEntryToFile(new LogEntry(1, 1, 3, "aaaaa"));
//		FileUtilities.writeLogEntryToFile(new LogEntry(2, 2, 1, "bbbbb"));
//		FileUtilities.writeLogEntryToFile(new LogEntry(3, 3, 2, "ccccc"));
//		FileUtilities.writePageToFile(new Page(1,1,"bbbbb"));
//		FileUtilities.writePageToFile(new Page(2,1,"ccccc"));
//		FileUtilities.writePageToFile(new Page(3,1,"aaaaa"));
//		
//		System.out.println(FileUtilities.readLogEntryFromFile(2));
//		System.out.println(FileUtilities.readLogEntryFromFile(3));
//		System.out.println(FileUtilities.readLogEntryFromFile(1));
//		
//		System.out.println(FileUtilities.readPageFromFile(1));
//		System.out.println(FileUtilities.readPageFromFile(2));
//		System.out.println(FileUtilities.readPageFromFile(3));
//	}
	
}
