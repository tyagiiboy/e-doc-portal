package com.edoc.backend.repositories;

import com.edoc.backend.entities.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

  @Query(
      value = "SELECT a FROM Admission a " +
          "WHERE a.user.id = ?1 " +
          "ORDER BY a.admissionId DESC"
  )
  Admission getAdmissionHistoryOfUserId(long id);
}
