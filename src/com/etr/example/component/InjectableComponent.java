package com.etr.example.component;

import com.etr.example.annotations.Autowired;
import com.etr.example.bean.BaseBean;
import com.etr.example.bean.NotABean;


public class InjectableComponent {
	@Autowired
	BaseBean baseBean;
	
	@Autowired
	NotABean notABean;
	
	public void flush() {
		System.out.println("Executing injectableComponentWrite..");
		baseBean.write();
		System.out.println("Executed base bean...");
	}
}
