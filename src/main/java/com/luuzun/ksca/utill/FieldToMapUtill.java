package com.luuzun.ksca.utill;

import java.lang.reflect.Field;
import java.util.Map;

public class FieldToMapUtill {
	public static final FieldToMapUtill instance = new FieldToMapUtill();
	private FieldToMapUtill() {}
	public static FieldToMapUtill getInstance(){
		return instance;
	}
	
	public Map<String, String> putAllField(Map<String, String> map, Object obj){
        for (Field field : obj.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try {
            	if(field.get(obj)!=null) {
            		map.put(field.getName(), field.get(obj).toString());
            	}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
        }
		return map;
	}
}