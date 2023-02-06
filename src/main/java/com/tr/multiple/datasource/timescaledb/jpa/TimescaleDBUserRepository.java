package com.tr.multiple.datasource.timescaledb.jpa;

import com.tr.multiple.datasource.postgresql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimescaleDBUserRepository extends JpaRepository<User, Integer> {
}
