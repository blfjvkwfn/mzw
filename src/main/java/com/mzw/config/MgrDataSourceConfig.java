package com.mzw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * manager data source config
 * @author mengzhaowei
 * @date 2017年11月16日下午4:16:26
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="mgrEntityManagerFactory",
        transactionManagerRef="mgrTransactionManager",
        basePackages= { "com.mzw.mgr" }) //设置Repository所在位置
public class MgrDataSourceConfig {
	private static final String DATASOURCE_NAME = "mgrDataSource";
	private static final String DATASOURCE_QUALIFIER = "mgrDataSource";
	private static final String CONFIGURATION_PROPERTIES_PREFIX = "spring.datasource.mgr";
	private static final String JDBC_NAME = "mgrJdbcTemplate";
	private static final String ENTITY_MANAGER_NAME = "mgrEntityManager";
	private static final String ENTITY_MANAGER_QUALIFIER = "mgrEntityManager";
	private static final String ENTITY_MANAGER_FACTORY_NAME = "mgrEntityManagerFactory";
	private static final String ENTITY_MANAGER_FACTORY_QUALIFIER = "mgrEntityManagerFactory";
	private static final String ENTITY_PACKAGE_NAME = "com.mzw.mgr.model";
	private static final String PERSISTENCE_UNIT = "mgrPersistenceUnit";
	private static final String TRANSACTION_MANAGER_NAME = "mgrTransactionManager";
	private static final String TRANSACTION_MANAGER_QUALIFIER = "mgrTransactionManager";

	@Bean(name = DATASOURCE_NAME)
    @Qualifier(DATASOURCE_QUALIFIER)
    @ConfigurationProperties(prefix=CONFIGURATION_PROPERTIES_PREFIX)
	@Primary
    public DataSource mgrDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Bean(name = JDBC_NAME)
    public JdbcTemplate mgrJdbcTemplate(
            @Qualifier(DATASOURCE_QUALIFIER) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = ENTITY_MANAGER_NAME)
    @Qualifier(ENTITY_MANAGER_QUALIFIER)
    @Primary
    public EntityManager mgrEntityManager(EntityManagerFactoryBuilder builder) {
        return mgrEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = ENTITY_MANAGER_FACTORY_NAME)
    @Qualifier(ENTITY_MANAGER_FACTORY_QUALIFIER)
    @Primary
    public LocalContainerEntityManagerFactoryBean mgrEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mgrDataSource())
                .properties(getVendorProperties(mgrDataSource()))
                .packages(ENTITY_PACKAGE_NAME) //设置实体类所在位置
                .persistenceUnit(PERSISTENCE_UNIT)
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = TRANSACTION_MANAGER_NAME)
    @Qualifier(TRANSACTION_MANAGER_QUALIFIER)
    @Primary
    public PlatformTransactionManager mgrTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(mgrEntityManagerFactory(builder).getObject());
    }
}