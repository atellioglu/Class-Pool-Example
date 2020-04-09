package com.etr.example.bean;

import com.etr.example.annotations.Bean;

@Bean
public class BaseBean {

	public void write() {
		System.out.println("BaseBean class is writing to console");
	}
}
