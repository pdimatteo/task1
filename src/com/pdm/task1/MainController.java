package com.pdm.task1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class MainController {

	private static int sleepTime = 1000; 
			
		
	public static void main(String[] args) throws SigarException, InterruptedException {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter sleep time (ms): ");
		sleepTime = reader.nextInt();
		
		new RamData(new Sigar());
		new CpuData(new Sigar()); 
		new NetworkData(new Sigar());
		
	    while(true) {
	    	String s = "";
	    	
	    	s = RamData.printMetric();
	    	s += "\n------------------\n"; 
	    	s += CpuData.printMetric();
	    	s += "\n------------------\n"; 
	    	s += NetworkData.printMetric();
	    	s += "\n\n";
	    	
	    	System.out.print(s);
	    	
	    	try {
	    	   PrintWriter writer = new PrintWriter("data.txt", "UTF-8");
	    	   writer.print(s);
	    	   writer.close();
	       } catch (FileNotFoundException e) {
	    	   e.printStackTrace();
	       } catch (UnsupportedEncodingException e) {
	    	   e.printStackTrace();
	       }  
	    	
	    	Thread.sleep(sleepTime);
	    }
	}
	
}
