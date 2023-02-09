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

    @GetMapping("/insert/performance/{insertNum}")
    public String insertPerformance(@PathVariable Integer insertNum) {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= insertNum; i++) {
            mysqlUserRepository.insert(i);
        }
        return "MySQL 插入性能：insert " + insertNum + "条，耗时：" + (System.currentTimeMillis() - start) + " ms";
    }

}
