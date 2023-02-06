package com.tr.multiple.datasource.timescaledb.controller;

import com.tr.multiple.datasource.postgresql.entity.User;
import com.tr.multiple.datasource.timescaledb.jpa.TimescaleDBUserRepository;
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
@RequestMapping("/timescaledb")
public class TimescaleDBController {

    @Resource
    private TimescaleDBUserRepository timescaleDBUserRepository;

    @GetMapping("/user/{id}")
    public User findUserByIdFromMysql(@PathVariable Integer id) {
        return timescaleDBUserRepository.findById(id).orElseThrow(() ->  new RuntimeException("No record found by id=" + id));
    }

}
