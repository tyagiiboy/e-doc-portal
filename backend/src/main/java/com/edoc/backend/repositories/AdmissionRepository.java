package com.edoc.backend.repositories;

import com.edoc.backend.entities.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface AdmissionRepository extends JpaRepository<Admission, Long> {
  @Query(
      value = "SELECT a FROM Admission a " +
          "WHERE a.user.id = ?1 " +
          "ORDER BY a.admissionId DESC"
  )
  List<Admission> getAdmissionHistoryByUserId(long id);
  List<Admission> findByUserId(Long userId);
  Optional<Admission> findTopByUserIdOrderByAdmissionIdDesc(Long id);
}
