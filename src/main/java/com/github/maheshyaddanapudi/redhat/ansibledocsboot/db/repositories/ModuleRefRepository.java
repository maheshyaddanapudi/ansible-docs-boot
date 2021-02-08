package com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.repositories;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.ModuleRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRefRepository extends JpaRepository<ModuleRef, Integer>{
	
	ModuleRef findByModuleName(String moduleName);
}