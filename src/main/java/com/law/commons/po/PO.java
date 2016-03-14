package com.law.commons.po;

import java.lang.reflect.Field;

public class PO {
	protected int tab = 0;
	
	public void getFields(){
		Class<?> clazz = this.getClass();
		Field[] fs = clazz.getDeclaredFields();
		for(Field f :fs){
			System.out.println(f.getName());
		}
		
		Class<?> pclazz = clazz.getSuperclass();
		Field[] pfs = pclazz.getDeclaredFields();
		for(Field f :pfs){
			System.out.println(f.getName());
		}
	}
	
}
