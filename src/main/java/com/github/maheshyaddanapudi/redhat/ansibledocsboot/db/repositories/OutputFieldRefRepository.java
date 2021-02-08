package com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.repositories;

import java.util.List;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.db.entities.OutputFieldRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OutputFieldRefRepository extends JpaRepository<OutputFieldRef, Integer>
{
	@Query(value = "SELECT * FROM output_field_ref WHERE parent_output_field_id = ?1", nativeQuery = true)
	List<OutputFieldRef> getByOutputFieldRef(int parentOutputFieldId);
	
	@Query(value = "SELECT * FROM output_field_ref WHERE parent_output_field_id is null and command_id = ?1", nativeQuery = true)
	List<OutputFieldRef> getByCommandRefAndOutputFieldRefNull(int commandId);

}
