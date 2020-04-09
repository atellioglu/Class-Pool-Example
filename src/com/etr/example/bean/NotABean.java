package com.etr.example.bean;

public class NotABean extends BaseBean {
	
	@Override
	public void write() {
		System.out.println("NotABean class is writing to console...");
	}

}
