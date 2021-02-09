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
@Table(name="command_ref")
public class CommandRef implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1166372403390084222L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "command_ref_id", updatable = false, nullable = false)
	private int commandId;
	
	@OneToOne
    @JoinColumn(name = "moduleId")
	@Fetch(FetchMode.SELECT)
	private ModuleRef moduleRef;
	
	@OneToOne
    @JoinColumn(name = "subModuleId", nullable = true)
	@Fetch(FetchMode.SELECT)
	private SubModuleRef subModuleRef;
	
	@Column(name = "command", nullable = false, unique = true)
	private String command;
	
	@Column(name = "command_description", nullable = true)
	private String commandDescription;
	
	@Column(name = "synopsis", nullable = false, unique = true)
	private String synopsis;
	
	@Column(name = "requirements", nullable = true)
	private String requirements;
	
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

	public CommandRef() {
		super();
	}

	public CommandRef(int commandId, ModuleRef moduleRef, SubModuleRef subModuleRef, String command,
			String commandDescription, String synopsis, String requirements, String refUrl, Date insertTimestamp,
			Date updateTimestamp) {
		super();
		this.commandId = commandId;
		this.moduleRef = moduleRef;
		this.subModuleRef = subModuleRef;
		this.command = command;
		this.commandDescription = commandDescription;
		this.synopsis = synopsis;
		this.requirements = requirements;
		this.refUrl = refUrl;
		this.insertTimestamp = insertTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	public ModuleRef getModuleRef() {
		return moduleRef;
	}

	public void setModuleRef(ModuleRef moduleRef) {
		this.moduleRef = moduleRef;
	}

	public SubModuleRef getSubModuleRef() {
		return subModuleRef;
	}

	public void setSubModuleRef(SubModuleRef subModuleRef) {
		this.subModuleRef = subModuleRef;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommandDescription() {
		return commandDescription;
	}

	public void setCommandDescription(String commandDescription) {
		this.commandDescription = commandDescription;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
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
		return "CommandRef [commandId=" + commandId + ", moduleRef=" + moduleRef + ", subModuleRef=" + subModuleRef
				+ ", command=" + command + ", commandDescription=" + commandDescription + ", synopsis=" + synopsis
				+ ", requirements=" + requirements + ", refUrl=" + refUrl + ", insertTimestamp=" + insertTimestamp
				+ ", updateTimestamp=" + updateTimestamp + "]";
	}
}
