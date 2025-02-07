package com.test.exception;


public class ResourseNotFoundException extends RuntimeException{

	private String resourceName;
	private String fieldName;
	private Integer fieldId;
	
	
	public ResourseNotFoundException(String resourceName, String fieldName, Integer fieldId) {
		super(String.format("%s data not found for fieldName %s where fieldId is %s",resourceName, fieldName,fieldId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public Integer getFieldId() {
		return fieldId;
	}
	
	
	
	
}
