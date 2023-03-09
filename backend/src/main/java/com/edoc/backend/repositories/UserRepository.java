package com.edoc.backend.repositories;

import com.edoc.backend.entities.User;
import com.edoc.backend.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByRole(Role role);
  Optional<User> findByIdAndRole(long id, Role role);

  Optional<User> findByUsername(String username);
  @Query(
      value = "SELECT u FROM User u JOIN u.school s WHERE u.role=?1 AND s.id=?2"
  )
  List<User> findByRoleAndSchoolDiseCode(Role role, long diseCode);
}
