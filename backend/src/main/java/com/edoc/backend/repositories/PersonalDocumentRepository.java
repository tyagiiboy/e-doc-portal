package com.edoc.backend.repositories;

import com.edoc.backend.entities.PersonalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDocumentRepository extends JpaRepository<PersonalDocument, Integer> {

}
