package com.tr.multiple.datasource.mysql.jpa;

import com.tr.multiple.datasource.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MysqlUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into emq_message values (:id, now(), 'msg')", nativeQuery = true)
    void insert(Integer id);

}
