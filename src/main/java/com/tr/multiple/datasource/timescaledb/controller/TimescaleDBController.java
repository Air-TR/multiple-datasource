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

    @GetMapping("/insert/performance/{insertNum}")
    public String insertPerformance(@PathVariable Integer insertNum) {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= insertNum; i++) {
            timescaleDBUserRepository.insert(i);
        }
        return "TimescaleDB 插入性能：insert " + insertNum + "条，耗时：" + (System.currentTimeMillis() - start) + " ms";
    }

}
