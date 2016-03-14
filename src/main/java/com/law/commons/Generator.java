package com.law.commons;

import java.util.Random;

public class Generator {
	public static String getSixRandomNumber(){
		Random r = new Random();
		int randomNum = r.nextInt(1000000);
		if(randomNum<10){
			return "00000"+randomNum;
		}
		if(randomNum<100){
			return "0000"+randomNum;
		}
		if(randomNum<1000){
			return "000"+randomNum;
		}
		if(randomNum<10000){
			return "00"+randomNum;
		}
		if(randomNum<100000){
			return "0"+randomNum;
		}else{
			return ""+randomNum;
		}
		 
	}
}
