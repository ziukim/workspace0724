package com.kh.jdbc.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileTestRun {
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		Properties propXml = new Properties();
		
		prop.setProperty("select", "SELECT * FROM TEST");
		
		try {
			prop.storeToXML(new FileOutputStream("resources/query.xml"), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
