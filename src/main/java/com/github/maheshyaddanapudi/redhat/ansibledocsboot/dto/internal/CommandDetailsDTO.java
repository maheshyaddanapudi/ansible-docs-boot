package com.github.maheshyaddanapudi.redhat.ansibledocsboot.dto.internal;

import java.io.Serializable;
import java.util.List;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.CommandRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.InputFieldRef;

public class CommandDetailsDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8235241772174244318L;
	private CommandRef command;
	private List<InputFieldRef> inputFields;
	private List<OutputFieldDTO> outputFields;
	
	public CommandDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommandDetailsDTO(CommandRef command, List<InputFieldRef> inputFields, List<OutputFieldDTO> outputFields) {
		super();
		this.command = command;
		this.inputFields = inputFields;
		this.outputFields = outputFields;
	}

	public CommandRef getCommand() {
		return command;
	}

	public void setCommand(CommandRef command) {
		this.command = command;
	}

	public List<InputFieldRef> getInputFields() {
		return inputFields;
	}

	public void setInputFields(List<InputFieldRef> inputFields) {
		this.inputFields = inputFields;
	}

	public List<OutputFieldDTO> getOutputFields() {
		return outputFields;
	}

	public void setOutputFields(List<OutputFieldDTO> outputFields) {
		this.outputFields = outputFields;
	}

	@Override
	public String toString() {
		return "CommandDetailsDTO [command=" + command + ", inputFields=" + inputFields + ", outputFields="
				+ outputFields + "]";
	}
}
