package com.edoc.backend.repositories;

import com.edoc.backend.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
  @Query(
      value = "select t from Transfer t where t.respondant.diseCode = ?1"
  )
  List<Transfer> findBySchoolDiseCode(long diseCode);
}
