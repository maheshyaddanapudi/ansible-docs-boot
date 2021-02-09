package com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.repositories;

import java.util.List;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.ModuleRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.SubModuleRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubModuleRefRepository  extends JpaRepository<SubModuleRef, Integer>{

	List<SubModuleRef> findByModuleRef(ModuleRef moduleRef);
	SubModuleRef findByModuleRefAndSubModuleName(ModuleRef moduleRef, String subModuleName);
}
