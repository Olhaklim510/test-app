package com.clearsolutions.testapp.repositories;

import com.clearsolutions.testapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    List<Users> findByBirthDateBetween(final LocalDate from, final LocalDate to);
}
