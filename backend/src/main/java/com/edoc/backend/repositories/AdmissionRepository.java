package com.edoc.backend.repositories;

import com.edoc.backend.entities.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    @Query(
            value = "select a from Admission a where a.admissionId = ?1 " +
                    "order by a.admissionId desc limit 1"
    )
    Admission getLatestAdmissionDetailsById(long userId);
}
