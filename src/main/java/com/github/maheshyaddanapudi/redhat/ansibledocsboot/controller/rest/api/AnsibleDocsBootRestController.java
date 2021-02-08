package com.github.maheshyaddanapudi.redhat.ansibledocsboot.controller.rest.api;

import java.util.ArrayList;
import java.util.List;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.constants.Constants;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.CommandRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.ModuleRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.SubModuleRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.dto.internal.CommandDetailsDTO;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.service.AnsibleDocsBootService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
@Profile(value = {"default", Constants.BASIC})
@Tag(name = "Ansible Command Detailer Controller", description = "The Ansible Moduler API")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AnsibleDocsBootRestController {

    private Logger logger = LoggerFactory.getLogger(AnsibleDocsBootRestController.class.getSimpleName());

    @Autowired
    private AnsibleDocsBootService ansibleDocsBootService;

    @Operation(summary = "ALL Modules List", description = "Get the list all Ansible Modules available", tags = { "modules" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained Modules List",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModuleRef.class)))) ,
            @ApiResponse(responseCode = "404", description = "Modules NOT Found - Returned when NO modules are found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Returned when an unexpected error occurs on server side", content = @Content())})
    @GetMapping(value = "modules", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<ModuleRef>> getAllModules()
    {
        try {

            List<ModuleRef> response = this.ansibleDocsBootService.getAllModules();

            if(null!=response && !response.isEmpty())
            {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Sub Modules List ", description = "Get the list all Sub Modules available or by Module Name", tags = { "sub-modules" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained Sub Modules List",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SubModuleRef.class)))) ,
            @ApiResponse(responseCode = "404", description = "Sub Modules NOT Found - Returned when NO Sub Modules are found for the given selection", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Returned when an unexpected error occurs on server side", content = @Content())})
    @GetMapping(value = "sub-modules", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<SubModuleRef>> getSubModulesByModuleName(@RequestParam(name = "module_name", required = false) String moduleName )
    {
        try {

            List<SubModuleRef> response = this.ansibleDocsBootService.getSubModulesByModuleName(moduleName);

            if(null!=response && !response.isEmpty())
            {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            else
                return new ResponseEntity(new ArrayList<SubModuleRef>(), HttpStatus.OK);

        }
        catch(Exception e)
        {
            logger.error(e.getMessage());

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Commands List", description = "Get the list all Commands available or by Module & Sub Module Names", tags = { "command" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained Commands List",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommandRef.class)))) ,
            @ApiResponse(responseCode = "404", description = "Commands NOT Found - Returned when NO commands are found for the given selection", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Returned when an unexpected error occurs on server side", content = @Content())})
    @GetMapping(value = "command", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<CommandRef>> getCommandsByModuleNameAndSubModuleName(@RequestParam(name = "module_name",required = false) String moduleName , @RequestParam(name = "sub_module_name", required = false) String subModuleName)
    {
        try {
            List<CommandRef> response = this.ansibleDocsBootService.getCommandsByModuleNameAndSubModuleName(moduleName, subModuleName);

            if(null!=response && !response.isEmpty())
            {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            logger.error(e.getMessage());

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Command Details", description = "Get Command details with Input and Output parameters by Module & Sub Module Names, Command", tags = { "command-details" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained Command Details",
                    content = @Content(schema = @Schema(implementation = CommandDetailsDTO.class))) ,
            @ApiResponse(responseCode = "404", description = "Command NOT Found - Returned when NO command details are found for the given selection", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Returned when an unexpected error occurs on server side", content = @Content())})
    @GetMapping(value = "command-details", produces = "application/json")
    @ResponseBody
    public ResponseEntity<CommandDetailsDTO> getCommandDetailsByModuleNameAndSubModuleNameAndCommand(@RequestParam(name = "module_name", required = true) String moduleName , @RequestParam(name = "sub_module_name", required = false) String subModuleName , @RequestParam(name = "command", required = true) String command)
    {
        try {

            CommandDetailsDTO response = this.ansibleDocsBootService.getCommandDetailsByModuleNameAndSubModuleNameAndCommand(moduleName, subModuleName, command);

            if(null!=response && null !=response.getCommand() && null !=response.getCommand().getCommand())
            {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            logger.error(e.getMessage());

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
