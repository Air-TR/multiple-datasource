package com.tr.multiple.datasource.postgresql.jpa;

import com.tr.multiple.datasource.postgresql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresqlUserRepository extends JpaRepository<User, Integer> {
}
