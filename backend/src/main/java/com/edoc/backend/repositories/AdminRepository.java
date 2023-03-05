package com.edoc.backend.repositories;

import com.edoc.backend.entities.Admin;
import com.edoc.backend.entities.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
