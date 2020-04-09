package com.etr.example;

import com.etr.example.bean.BaseBean;
import com.etr.example.component.InjectableComponent;
import com.etr.example.core.ApplicationContext;

public class Application {

	public static void main(String[] args) throws Exception{
		ApplicationContext context = ApplicationContext.getInstance();
		context.load("com.etr.example.bean");
		
		BaseBean baseBean =(BaseBean)context.getClassInstance("BaseBean");
		baseBean.write();
		
		InjectableComponent injectableComponent = new InjectableComponent();
		ApplicationContext.getInstance().inject(injectableComponent);
		injectableComponent.flush();
	}
}
