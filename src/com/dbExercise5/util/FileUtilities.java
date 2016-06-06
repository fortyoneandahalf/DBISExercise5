package com.dbExercise5.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.dbExercise5.logging.LogEntry;

public class FileUtilities {
	
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
	
	public static LogEntry readLogEntryFromFile(String logEntryFileName){
		LogEntry le = null;
		String leStr = FileUtilities.readFromFile("log/"+logEntryFileName+".txt");
		String[] leStrArr = leStr.split(",");
		le = new LogEntry(Integer.parseInt(leStrArr[0]), Integer.parseInt(leStrArr[1]), Integer.parseInt(leStrArr[2]), leStrArr[3]);
		return le;
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
	
	/**
	 * Testing - Yay - It works!
	 * @param a
	 */
//	public static void main(String a[]){
//		FileUtilities.writeToFile("data/a.txt", "Hello There!");
//		System.out.println(FileUtilities.readFromFile("data/a.txt"));// writeToFile("data/a.txt", "Hello There!");
//		
//	}
	
}
