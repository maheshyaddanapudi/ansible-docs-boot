package com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "input_field_ref")
public class InputFieldRef implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4540693586615711084L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "input_field_id", updatable = false, nullable = false)
	private int inputFieldId;
	
	@OneToOne
    @JoinColumn(name = "commandId")
	@Fetch(FetchMode.SELECT)
	private CommandRef commandRef;
	
	@Column(name = "field_name", nullable = false)
	private String fieldName;
	
	@Column(name = "field_description", nullable = true)
	private String fieldDescription;
	
	@Column(name = "field_type", nullable = true)
	private String fieldType;
	
	@Column(name = "default_value", nullable = true)
	private String defaultValue;
	
	@Column(name = "choices", nullable = true)
	private String choices;
	
	@Column(name = "ref_url", nullable = true)
	private String refUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_timestamp", nullable = false)
	private Date insertTimestamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_timestamp", nullable = false)
	private Date updateTimestamp;

	@PrePersist
	protected void onCreate() {
		updateTimestamp = insertTimestamp = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updateTimestamp = new Date();
	}

	public InputFieldRef() {
		super();
	}

	public InputFieldRef(int inputFieldId, CommandRef commandRef, String fieldName, String fieldDescription,
			String fieldType, String defaultValue, String choices, String refUrl, Date insertTimestamp,
			Date updateTimestamp) {
		super();
		this.inputFieldId = inputFieldId;
		this.commandRef = commandRef;
		this.fieldName = fieldName;
		this.fieldDescription = fieldDescription;
		this.fieldType = fieldType;
		this.defaultValue = defaultValue;
		this.choices = choices;
		this.refUrl = refUrl;
		this.insertTimestamp = insertTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public int getInputFieldId() {
		return inputFieldId;
	}

	public void setInputFieldId(int inputFieldId) {
		this.inputFieldId = inputFieldId;
	}

	public CommandRef getCommandRef() {
		return commandRef;
	}

	public void setCommandRef(CommandRef commandRef) {
		this.commandRef = commandRef;
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
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

	@Override
	public String toString() {
		return "InputFieldRef [inputFieldId=" + inputFieldId + ", commandRef=" + commandRef + ", fieldName=" + fieldName
				+ ", fieldDescription=" + fieldDescription + ", fieldType=" + fieldType + ", defaultValue="
				+ defaultValue + ", choices=" + choices + ", refUrl=" + refUrl + ", insertTimestamp=" + insertTimestamp
				+ ", updateTimestamp=" + updateTimestamp + "]";
	}
}
