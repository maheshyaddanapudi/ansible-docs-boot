package com.github.maheshyaddanapudi.redhat.ansibledocsboot.dto.internal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OutputFieldDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8790798828939288808L;
	private int outputFieldId;
	private int commandId;
	private int parentOutputFieldRef;
	private String fieldName;
	private String fieldDescription;
	private String fieldType;
	private String returnedAlways;
	private String refUrl;
	private Date insertTimestamp;
	private Date updateTimestamp;
	private List<OutputFieldDTO> children;
	
	
	public OutputFieldDTO(int outputFieldId, int commandId, int parentOutputFieldRef, String fieldName,
			String fieldDescription, String fieldType, String returnedAlways, String refUrl, Date insertTimestamp,
			Date updateTimestamp, List<OutputFieldDTO> children) {
		super();
		this.outputFieldId = outputFieldId;
		this.commandId = commandId;
		this.parentOutputFieldRef = parentOutputFieldRef;
		this.fieldName = fieldName;
		this.fieldDescription = fieldDescription;
		this.fieldType = fieldType;
		this.returnedAlways = returnedAlways;
		this.refUrl = refUrl;
		this.insertTimestamp = insertTimestamp;
		this.updateTimestamp = updateTimestamp;
		this.children = children;
	}


	public OutputFieldDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getOutputFieldId() {
		return outputFieldId;
	}


	public void setOutputFieldId(int outputFieldId) {
		this.outputFieldId = outputFieldId;
	}


	public int getCommandId() {
		return commandId;
	}


	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}


	public int getParentOutputFieldRef() {
		return parentOutputFieldRef;
	}


	public void setParentOutputFieldRef(int parentOutputFieldRef) {
		this.parentOutputFieldRef = parentOutputFieldRef;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public String getFieldDescription() {
		return fieldDescription;
	}


	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}


	public String getFieldType() {
		return fieldType;
	}


	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}


	public String getReturnedAlways() {
		return returnedAlways;
	}


	public void setReturnedAlways(String returnedAlways) {
		this.returnedAlways = returnedAlways;
	}


	public String getRefUrl() {
		return refUrl;
	}


	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}


	public Date getInsertTimestamp() {
		return insertTimestamp;
	}


	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}


	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}


	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}


	public List<OutputFieldDTO> getChildren() {
		return children;
	}


	public void setChildren(List<OutputFieldDTO> children) {
		this.children = children;
	}


	@Override
	public String toString() {
		return "OutputFieldDTO [outputFieldId=" + outputFieldId + ", commandId=" + commandId + ", parentOutputFieldRef="
				+ parentOutputFieldRef + ", fieldName=" + fieldName + ", fieldDescription=" + fieldDescription
				+ ", fieldType=" + fieldType + ", returnedAlways=" + returnedAlways + ", refUrl=" + refUrl
				+ ", insertTimestamp=" + insertTimestamp + ", updateTimestamp=" + updateTimestamp + ", children="
				+ children + "]";
	}

}
