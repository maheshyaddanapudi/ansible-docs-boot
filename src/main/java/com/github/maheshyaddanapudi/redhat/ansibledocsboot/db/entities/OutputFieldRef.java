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
@Table(name="output_field_ref")
public class OutputFieldRef implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6662228203063233580L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "output_field_id", updatable = false, nullable = false)
	private int outputFieldId;
	
	@OneToOne
    @JoinColumn(name = "commandId")
	@Fetch(FetchMode.SELECT)
	private CommandRef commandRef;
	
	@OneToOne
    @JoinColumn(name = "outputFieldId", nullable = false, referencedColumnName = "parent_output_field_id")
	@Fetch(FetchMode.SELECT)
	private OutputFieldRef outputFieldRef;
	
	@Column(name = "field_name", nullable = false)
	private String fieldName;
	
	@Column(name = "field_description", nullable = true)
	private String fieldDescription;
	
	@Column(name = "field_type", nullable = true)
	private String fieldType;
	
	@Column(name = "returned_always", nullable = true)
	private String returnedAlways;
	
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

	public OutputFieldRef() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutputFieldRef(int outputFieldId, CommandRef commandRef, OutputFieldRef outputFieldRef, String fieldName,
			String fieldDescription, String fieldType, String returnedAlways, String refUrl, Date insertTimestamp,
			Date updateTimestamp) {
		super();
		this.outputFieldId = outputFieldId;
		this.commandRef = commandRef;
		this.outputFieldRef = outputFieldRef;
		this.fieldName = fieldName;
		this.fieldDescription = fieldDescription;
		this.fieldType = fieldType;
		this.returnedAlways = returnedAlways;
		this.refUrl = refUrl;
		this.insertTimestamp = insertTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public int getOutputFieldId() {
		return outputFieldId;
	}

	public void setOutputFieldId(int outputFieldId) {
		this.outputFieldId = outputFieldId;
	}

	public CommandRef getCommandRef() {
		return commandRef;
	}

	public void setCommandRef(CommandRef commandRef) {
		this.commandRef = commandRef;
	}

	public OutputFieldRef getOutputFieldRef() {
		return outputFieldRef;
	}

	public void setOutputFieldRef(OutputFieldRef outputFieldRef) {
		this.outputFieldRef = outputFieldRef;
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

	@Override
	public String toString() {
		return "OutputFieldRef [outputFieldId=" + outputFieldId + ", commandRef=" + commandRef + ", outputFieldRef="
				+ outputFieldRef + ", fieldName=" + fieldName + ", fieldDescription=" + fieldDescription
				+ ", fieldType=" + fieldType + ", returnedAlways=" + returnedAlways + ", refUrl=" + refUrl
				+ ", insertTimestamp=" + insertTimestamp + ", updateTimestamp=" + updateTimestamp + "]";
	}

}
