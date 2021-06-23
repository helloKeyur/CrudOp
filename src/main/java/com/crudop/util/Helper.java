package com.crudop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.Part;

import java.io.File;

public class Helper {
	
	public static String getCurrentTimestamp() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateObj = new Date();
		return df.format(dateObj);
	}
	
	public static String getDate(String dateStr) {
		String formattedDate = null;
		
		try {
			Date dateObj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
			formattedDate = formatter.format(dateObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return formattedDate;
	}
	
	public static boolean saveFile(Part part, String path) {
		boolean status = false;
		try {
			File uploadDir = new File(path);
            if(!uploadDir.exists()) uploadDir.mkdir();
            part.write(path);
			status = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	public static boolean deleteFile(String path){
        boolean status = false;
        try{
            File file = new File(path);
            status = file.delete(); // delete() method return boolean so.
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    
    public static String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }
    
    public static String getRandomString(int length){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
          int index = random.nextInt(alphabet.length());
          char randomChar = alphabet.charAt(index);
          sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString.toLowerCase();
    }
    
    //overload
    public static String getRandomString(){
        return getRandomString(10);
    }
}
