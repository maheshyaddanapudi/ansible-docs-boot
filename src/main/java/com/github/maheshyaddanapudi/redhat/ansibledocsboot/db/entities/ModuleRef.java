package com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "module_ref")

public class ModuleRef implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -820128963575437203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "module_id", updatable = false, nullable = false)
	private int moduleId;
	
	@Column(name = "module_name", nullable = false, unique = true)
	private String moduleName;
	
	@Column(name = "module_description", nullable = true)
	private String moduleDescription;
	
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

	public ModuleRef() {
		super();
	}

	public ModuleRef(int moduleId, String moduleName, String moduleDescription, String refUrl, Date insertTimestamp,
			Date updateTimestamp) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleDescription = moduleDescription;
		this.refUrl = refUrl;
		this.insertTimestamp = insertTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
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
		return "ModuleRef [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleDescription="
				+ moduleDescription + ", refUrl=" + refUrl + ", insertTimestamp=" + insertTimestamp
				+ ", updateTimestamp=" + updateTimestamp + "]";
	}
}
