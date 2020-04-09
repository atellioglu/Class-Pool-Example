package com.etr.example.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.etr.example.annotations.Autowired;
import com.etr.example.annotations.Bean;
import com.etr.example.util.ClassFinder;

public class ApplicationContext {
	private static ApplicationContext instance = null;
	private Map<String, Object> classMap;
	public static ApplicationContext getInstance() {
		if(instance == null) {
			instance = new ApplicationContext();
		}
		return instance;
	}
	private ApplicationContext() {
		classMap = new HashMap<>();
	}
	public void load(String basePackageName) throws InstantiationException, IllegalAccessException {
		List<Class<?>> find = ClassFinder.find(basePackageName);
		for(Class<?> clazz: find) {
			if(clazz.isAnnotationPresent(Bean.class)) {
				System.out.println(clazz.getSimpleName() + " is a bean. Inserting into map");
				classMap.put(clazz.getSimpleName(), clazz.newInstance());
			}else {
				System.out.println(clazz.getSimpleName() +" is not a bean.");
			}
		}
	}
	public void inject(Object object) throws Exception {
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field: fields) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(Autowired.class)) {
				System.out.println("Found injectable variable with type=["+field.getType().getSimpleName()+"]");
			}
			Object foundBeanInMap = getClassInstance(field.getType().getSimpleName());
			if(foundBeanInMap == null) {
				throw new IllegalAccessException(field.getType().getSimpleName()+" bean is not marked as bean");
			}
			field.set(object, foundBeanInMap);
		}
	}
	public Object find(Class<?> clazz) {
		for(Map.Entry<String, Object> entry: classMap.entrySet()) {
			if(entry.getKey().equals(clazz.getSimpleName())) {
				return entry.getValue();
			}
		}
		return null;
	}
	public Object getClassInstance(String className) {
		return classMap.get(className);
	}
	
}
