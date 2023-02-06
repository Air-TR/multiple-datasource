package com.tr.multiple.datasource.postgresql.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PostgreSQL 中实体类必须要指定 schema，所以不能共用 com.tr.multiple.datasource.mysql.entity.User，必须单独创建使用
 *
 * @author taorun
 * @date 2023/2/6 11:03
 */
@Data
@Entity
@Table(name = "user", schema = "public") // PostgreSQL 中实体类必须要指定 schema
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
