package com.tr.multiple.datasource.mysql.jpa;

import com.tr.multiple.datasource.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlUserRepository extends JpaRepository<User, Integer> {
}
