package com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.repositories;

import java.util.List;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.CommandRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.ModuleRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.SubModuleRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRefRepository extends JpaRepository<CommandRef, Integer>{
	
	List<CommandRef> findByModuleRefAndSubModuleRef(ModuleRef moduleRef, SubModuleRef subModuleRef);
	List<CommandRef> findByCommand(String command);
	
	CommandRef findByModuleRefAndSubModuleRefAndCommand(ModuleRef moduleRef, SubModuleRef subModuleRef, String command);
}