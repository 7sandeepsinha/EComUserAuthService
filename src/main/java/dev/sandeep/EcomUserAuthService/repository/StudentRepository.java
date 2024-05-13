package dev.sandeep.EcomUserAuthService.repository;

import dev.sandeep.EcomUserAuthService.entity.Role;
import dev.sandeep.EcomUserAuthService.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
