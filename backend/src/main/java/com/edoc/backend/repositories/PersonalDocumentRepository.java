package com.edoc.backend.repositories;

import com.edoc.backend.entities.PersonalDocument;
import com.edoc.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PersonalDocumentRepository extends JpaRepository<PersonalDocument, Integer> {
  Optional<PersonalDocument> findByUserId(long id);
}
