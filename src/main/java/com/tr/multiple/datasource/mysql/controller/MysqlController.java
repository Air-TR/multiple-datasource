package com.tr.multiple.datasource.mysql.controller;

import com.tr.multiple.datasource.mysql.entity.User;
import com.tr.multiple.datasource.mysql.jpa.MysqlUserRepository;
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
@RequestMapping("/mysql")
public class MysqlController {

    @Resource
    private MysqlUserRepository mysqlUserRepository;

    @GetMapping("/user/{id}")
    public User findUserByIdFromMysql(@PathVariable Integer id) {
        return mysqlUserRepository.findById(id).orElseThrow(() ->  new RuntimeException("No record found by id=" + id));
    }

}
