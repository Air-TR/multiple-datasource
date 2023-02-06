package com.tr.multiple.datasource.timescaledb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @EnableJpaRepositories:
 *  entityManagerFactoryRef: 实体管理工厂引用名称，对应到 @Bean 注解对应的方法
 *  transactionManagerRef: 事务管理工厂引用名称，对应到 @Bean 注解对应的方法
 *  basePackage: 用于配置扫描 Repositories 所在的 package 及子 package
 *
 * @author taorun
 * @date 2023/2/6 11:08
 */
@Configuration
@EnableTransactionManagement // 使用注解 @EnableTransactionManagement 开启事务支持后，可以在访问数据库的 Service 方法上添加注解 @Transactional 使用事务
@EnableJpaRepositories(entityManagerFactoryRef = "timescaledbEntityManagerFactory", transactionManagerRef = "timescaledbTransactionManager", basePackages = {"com.tr.multiple.datasource.timescaledb.jpa"})
public class TimescaleDBConfig {

    @Bean(name = "timescaledbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.timescaledb")
    public DataSource timescaledbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "timescaledbJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa.postgresql") // JpaProperties 配置与 postgresql 一样
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    /**
     * 获取主库实体管理工厂对象
     *
     * @param builder
     * @param dataSource
     * @param jpaProperties
     * @return
     */
    @Bean(name = "timescaledbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("timescaledbDataSource") DataSource dataSource, @Qualifier("timescaledbJpaProperties") JpaProperties jpaProperties) {
        return builder
                .dataSource(dataSource) // 设置数据源
                .properties(jpaProperties.getProperties()) // 设置 jpa 配置
                .packages("com.tr.multiple.datasource.postgresql.entity") // 设置实体包路径（timescaledb 可以共用 postgresql 实体）
                .persistenceUnit("timescaledb") // 设置持久化单元名，用于 @PersistenceContext 注解获取 EntityManager 时指定数据源
                .build();
    }

    /**
     * 获取主库事务管理对象
     *
     * @param entityManagerFactory
     * @return
     */
    @Bean(name = "timescaledbTransactionManager")
    public PlatformTransactionManager timescaledbTransactionManager(@Qualifier("timescaledbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
