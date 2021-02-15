package com.github.maheshyaddanapudi.redhat.ansibledocsboot.service;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.constants.Constants;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.*;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.repositories.*;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.dto.internal.CommandDetailsDTO;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.dto.internal.OutputFieldDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile(value = {"default", Constants.BASIC})
@CacheConfig(cacheNames = "ansible-docs-boot")
public class AnsibleDocsBootService {

    private Logger logger = LoggerFactory.getLogger(AnsibleDocsBootService.class.getSimpleName());

    @Autowired
    private ModuleRefRepository moduleRefRepository;

    @Autowired
    private SubModuleRefRepository subModuleRefRepository;

    @Autowired
    private CommandRefRepository commandRefRepository;

    @Autowired
    private InputFieldRefRepository inputFieldRefRepository;

    @Autowired
    private OutputFieldRefRepository outputFieldRefRepository;

    @Cacheable
    public List<ModuleRef> getAllModules()
    {
        logger.info(">>getAllModules()");
        List<ModuleRef> moduleRefs = this.moduleRefRepository.findAll();
        logger.info("<<getAllModules()");
        return moduleRefs;
    }

    @Cacheable
    public List<SubModuleRef> getSubModulesByModuleName(String moduleName )
    {
        logger.info(">>getSubModulesByModuleName(): " + moduleName);

        if(null!=moduleName && !"".equalsIgnoreCase(moduleName))
        {
            ModuleRef moduleRef = this.moduleRefRepository.findByModuleName(moduleName);

            if( null != moduleRef && moduleRef.getModuleId() > 0)
            {
                logger.info("Module Ref for the Module Name provided: " + moduleRef.toString());

                logger.info("<<getSubModulesByModuleName()");
                return this.subModuleRefRepository.findByModuleRef(moduleRef);
            }
            else
            {
                logger.info("<<getSubModulesByModuleName()");
                return new ArrayList<SubModuleRef>();
            }
        }
        else
            return this.subModuleRefRepository.findAll();
    }

    @Cacheable
    public List<CommandRef> getCommandsByModuleNameAndSubModuleName(String moduleName , String subModuleName)
    {
        logger.info(">>getCommandsByModuleNameAndSubModuleName()");

        if(null!=moduleName)
        {
            ModuleRef moduleRef = this.moduleRefRepository.findByModuleName(moduleName);

            if(null!=moduleRef && null != subModuleName )
            {
                SubModuleRef subModuleRef = this.subModuleRefRepository.findByModuleRefAndSubModuleName(moduleRef, subModuleName);

                if(null != subModuleRef && subModuleRef.getSubModuleId() > 0)
                {
                    return this.commandRefRepository.findByModuleRefAndSubModuleRef(moduleRef, subModuleRef);
                }
                else
                {
                    return this.commandRefRepository.findByModuleRefAndSubModuleRef(moduleRef, null);
                }
            }
            else if(null!=moduleRef)
            {
                return this.commandRefRepository.findByModuleRefAndSubModuleRef(moduleRef, null);
            }
        }

        logger.info("<<getCommandsByModuleNameAndSubModuleName()");
        return this.commandRefRepository.findAll();
    }

    @Cacheable
    public CommandDetailsDTO getCommandDetailsByModuleNameAndSubModuleNameAndCommand(String moduleName , String subModuleName , String command)
    {
        logger.info(">>getCommandDetailsByModuleNameAndSubModuleNameAndCommand()");

        ModuleRef moduleRef = this.moduleRefRepository.findByModuleName(moduleName);

        CommandRef commandRef = new CommandRef();

        CommandDetailsDTO commandDetails = new CommandDetailsDTO();

        if( null != subModuleName )
        {
            SubModuleRef subModuleRef = this.subModuleRefRepository.findByModuleRefAndSubModuleName(moduleRef, subModuleName);

            if( null != subModuleRef && subModuleRef.getSubModuleId() > 0)
            {
                commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, subModuleRef, command);
            }
            else
            {
                commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, null, command);
            }
        }
        else
        {
            commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, null, command);
        }

        if( null != commandRef && commandRef.getCommandId() > 0 )
        {
            commandDetails.setCommand(commandRef);
            commandDetails.setInputFields(getInputFieldsByModuleNameAndSubModuleNameAndCommand(moduleName, subModuleName, command));
            commandDetails.setOutputFields(getOutputFieldsListByModuleNameAndSubModuleNameAndCommand(moduleName, subModuleName, command));
            logger.info("<<getCommandDetailsByModuleNameAndSubModuleNameAndCommand()");
            return commandDetails;
        }
        else {
            logger.info("<<getCommandDetailsByModuleNameAndSubModuleNameAndCommand()");
            return null;
        }
    }

    private List<InputFieldRef> getInputFieldsByModuleNameAndSubModuleNameAndCommand(@RequestParam(name = "module_name") String moduleName , @RequestParam(name = "sub_module_name") String subModuleName , @RequestParam (name = "command") String command)
    {
        logger.info(">>getInputFieldsByModuleNameAndSubModuleNameAndCommand()");
        ModuleRef moduleRef = this.moduleRefRepository.findByModuleName(moduleName);

        CommandRef commandRef = new CommandRef();

        if( null != subModuleName )
        {
            SubModuleRef subModuleRef = this.subModuleRefRepository.findByModuleRefAndSubModuleName(moduleRef, subModuleName);

            if( null != subModuleRef && subModuleRef.getSubModuleId() > 0)
            {
                commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, subModuleRef, command);
            }
            else
            {
                commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, null, command);
            }
        }
        else
        {
            commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, null, command);
        }

        if( null != commandRef )
        {
            logger.info("<<getInputFieldsByModuleNameAndSubModuleNameAndCommand()");
            return this.inputFieldRefRepository.findByCommandRef(commandRef);
        }
        else
        {
            logger.info("<<getInputFieldsByModuleNameAndSubModuleNameAndCommand()");
            return new ArrayList<InputFieldRef>();
        }
    }

    private List<OutputFieldDTO> getOutputFieldsListByModuleNameAndSubModuleNameAndCommand(@RequestParam(name = "module_name") String moduleName , @RequestParam(name = "sub_module_name", required = false) String subModuleName , @RequestParam (name = "command") String command)
    {
        logger.info(">>getOutputFieldsListByModuleNameAndSubModuleNameAndCommand()");
        ModuleRef moduleRef = this.moduleRefRepository.findByModuleName(moduleName);

        List<OutputFieldDTO> outputFields = new ArrayList<OutputFieldDTO>();

        CommandRef commandRef = new CommandRef();

        if( null != subModuleName )
        {
            SubModuleRef subModuleRef = this.subModuleRefRepository.findByModuleRefAndSubModuleName(moduleRef, subModuleName);

            if( null != subModuleRef && subModuleRef.getSubModuleId() > 0)
            {
                commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, subModuleRef, command);
            }
            else
            {
                commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, null, command);
            }
        }
        else
        {
            commandRef = this.commandRefRepository.findByModuleRefAndSubModuleRefAndCommand(moduleRef, null, command);
        }

        if( null != commandRef )
        {
            List<OutputFieldRef> outputFieldRefList = this.outputFieldRefRepository.getByCommandRefAndOutputFieldRefNull(commandRef.getCommandId());

            for(OutputFieldRef anOutputFieldRef : outputFieldRefList)
            {
                OutputFieldDTO outputField = new OutputFieldDTO();

                outputField.setOutputFieldId(anOutputFieldRef.getOutputFieldId());
                outputField.setCommandId(anOutputFieldRef.getCommandRef().getCommandId());
                outputField.setParentOutputFieldRef(anOutputFieldRef.getOutputFieldRef().getOutputFieldId());
                outputField.setFieldName(anOutputFieldRef.getFieldName());
                outputField.setFieldType(anOutputFieldRef.getFieldType());
                outputField.setFieldDescription(anOutputFieldRef.getFieldDescription());
                outputField.setReturnedAlways(anOutputFieldRef.getReturnedAlways());
                outputField.setInsertTimestamp(anOutputFieldRef.getInsertTimestamp());
                outputField.setUpdateTimestamp(anOutputFieldRef.getUpdateTimestamp());
                outputFields.add(outputField);

                if("complex".equalsIgnoreCase(anOutputFieldRef.getFieldType()))
                {

                    for(OutputFieldDTO aDTO: getChildrenAsList(anOutputFieldRef.getOutputFieldId())) {

                        aDTO.setFieldName(anOutputFieldRef.getFieldName()+"."+aDTO.getFieldName());

                        outputFields.add(aDTO);

                    }

                }
            }

            logger.info("<<getOutputFieldsListByModuleNameAndSubModuleNameAndCommand()");

            return outputFields;
        }
        else
        {
            logger.info("<<getOutputFieldsListByModuleNameAndSubModuleNameAndCommand()");

            return outputFields;
        }
    }

    private List<OutputFieldDTO> getChildrenAsList(int parentOutputFieldId)
    {
        logger.info(">>getChildrenAsList()");
        List<OutputFieldDTO> childrenList = new ArrayList<OutputFieldDTO>();

        try {

            List<OutputFieldRef> outputFieldsChildrenList = this.outputFieldRefRepository.getByOutputFieldRef(parentOutputFieldId);

            for(OutputFieldRef anOutputFieldRef: outputFieldsChildrenList)
            {
                OutputFieldDTO outputField = new OutputFieldDTO();

                outputField.setOutputFieldId(anOutputFieldRef.getOutputFieldId());
                outputField.setCommandId(anOutputFieldRef.getCommandRef().getCommandId());
                outputField.setParentOutputFieldRef(parentOutputFieldId);
                outputField.setFieldName(anOutputFieldRef.getFieldName());
                outputField.setFieldType(anOutputFieldRef.getFieldType());
                outputField.setFieldDescription(anOutputFieldRef.getFieldDescription());
                outputField.setReturnedAlways(anOutputFieldRef.getReturnedAlways());
                outputField.setInsertTimestamp(anOutputFieldRef.getInsertTimestamp());
                outputField.setUpdateTimestamp(anOutputFieldRef.getUpdateTimestamp());

                childrenList.add(outputField);

                if("complex".equalsIgnoreCase(anOutputFieldRef.getFieldType()))
                {
                    for(OutputFieldDTO aDTO: getChildrenAsList(anOutputFieldRef.getOutputFieldId())) {

                        aDTO.setFieldName(anOutputFieldRef.getFieldName()+"."+aDTO.getFieldName());

                        childrenList.add(aDTO);

                    }
                }
            }
            logger.info("<<getChildrenAsList()");
            return childrenList;
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            logger.info("<<getChildrenAsList()");
            return childrenList;
        }
    }
}
