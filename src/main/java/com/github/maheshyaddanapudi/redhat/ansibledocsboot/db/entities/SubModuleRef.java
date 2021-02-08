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
@Table(name = "sub_module_ref")
public class SubModuleRef implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -2932958251512205013L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_module_id", updatable = false, nullable = false)
	private int subModuleId;
	
	@OneToOne
    @JoinColumn(name = "moduleId")
	@Fetch(FetchMode.SELECT)
	private ModuleRef moduleRef;
	
	@Column(name = "sub_module_name", nullable = false, unique = true)
	private String subModuleName;
	
	@Column(name = "sub_module_description", nullable = true)
	private String subModuleDescription;
	
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

	public SubModuleRef() {
		super();
	}

	public SubModuleRef(int subModuleId, ModuleRef moduleRef, String subModuleName, String subModuleDescription,
			String refUrl, Date insertTimestamp, Date updateTimestamp) {
		super();
		this.subModuleId = subModuleId;
		this.moduleRef = moduleRef;
		this.subModuleName = subModuleName;
		this.subModuleDescription = subModuleDescription;
		this.refUrl = refUrl;
		this.insertTimestamp = insertTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public int getSubModuleId() {
		return subModuleId;
	}

	public void setSubModuleId(int subModuleId) {
		this.subModuleId = subModuleId;
	}

	public ModuleRef getModuleRef() {
		return moduleRef;
	}

	public void setModuleRef(ModuleRef moduleRef) {
		this.moduleRef = moduleRef;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getSubModuleDescription() {
		return subModuleDescription;
	}

	public void setSubModuleDescription(String subModuleDescription) {
		this.subModuleDescription = subModuleDescription;
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
		return "SubModuleRef [subModuleId=" + subModuleId + ", moduleRef=" + moduleRef + ", subModuleName=" + subModuleName
				+ ", subModuleDescription=" + subModuleDescription + ", refUrl=" + refUrl + ", insertTimestamp="
				+ insertTimestamp + ", updateTimestamp=" + updateTimestamp + "]";
	}
}
