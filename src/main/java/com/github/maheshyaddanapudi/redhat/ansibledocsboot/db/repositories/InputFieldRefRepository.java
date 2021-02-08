package com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.repositories;

import java.util.List;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.CommandRef;
import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.InputFieldRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputFieldRefRepository extends JpaRepository<InputFieldRef, Integer>{

	List<InputFieldRef> findByCommandRef(CommandRef commandRef);
}
