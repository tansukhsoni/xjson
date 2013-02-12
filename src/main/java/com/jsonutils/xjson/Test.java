package com.jsonutils.xjson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Test{
	public static void main(String [] args)
	{
		try {
		      FileInputStream fstream = new FileInputStream("/home/tansukh/Desktop/test/test.txt");
		      DataInputStream in = new DataInputStream(fstream);
		      BufferedReader br = new BufferedReader(new InputStreamReader(in));
		      FileInputStream fstream2 = new FileInputStream ("/home/tansukh/Desktop/test/test3.txt");
		      DataInputStream in2 = new DataInputStream(fstream2);
		      BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		      String str;
		      StringBuffer strContent = new StringBuffer("");
		      while ((str = br.readLine()) != null) {
		    	  
		    	  int cursor=0;
		    	  while(cursor<15)
		    	  {
		    		  strContent.append(str.charAt(cursor));
		    		  cursor++;
		    	  }
		    	  String str2=br2.readLine();
		    	  str2=("     "+str2).substring(str2.length());
		    	  strContent.append(str2);
		    	  cursor=20;
		    	  while(cursor<str.length())
		    	  {
		    		  strContent.append(str.charAt(cursor));
		    		  cursor++;
		    	  }
		    	  
		    	  strContent.append("\n");
		        
		      }
		      String str3=strContent.substring(0,strContent.length());
		      System.out.println(str3);
	          File file= new File("/home/tansukh/Desktop/test/resultFile.txt");
		      if (!file.exists()) {
					file.createNewFile();
				}
		      FileWriter fw = new FileWriter(file);
			  BufferedWriter bw = new BufferedWriter(fw);
			  bw.write(str3);
			  bw.close();
		      in.close();
		    } catch (Exception e) {
		      System.err.println(e);
		    }
		
		}
}