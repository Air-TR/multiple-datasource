package com.tr.multiple.datasource.postgresql.controller;

import com.tr.multiple.datasource.postgresql.entity.User;
import com.tr.multiple.datasource.postgresql.jpa.PostgresqlUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author taorun
 * @date 2023/2/6 11:23
 */
@RestController
@RequestMapping("/postgresql")
public class PostgresqlController {

    @Resource
    private PostgresqlUserRepository postgresqlUserRepository;

    @GetMapping("/user/{id}")
    public User findUserByIdFromMysql(@PathVariable Integer id) {
        return postgresqlUserRepository.findById(id).orElseThrow(() ->  new RuntimeException("No record found by id=" + id));
    }

}
