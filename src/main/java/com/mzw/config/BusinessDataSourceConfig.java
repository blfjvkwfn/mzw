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
        entityManagerFactoryRef="businessEntityManagerFactory",
        transactionManagerRef="businessTransactionManager",
        basePackages= { "com.mzw.business" }) //设置Repository所在位置
public class BusinessDataSourceConfig {
	private static final String DATASOURCE_NAME = "businessDataSource";
	private static final String DATASOURCE_QUALIFIER = "businessDataSource";
	private static final String CONFIGURATION_PROPERTIES_PREFIX = "spring.datasource.business";
	private static final String JDBC_NAME = "businessJdbcTemplate";
	private static final String ENTITY_MANAGER_NAME = "businessEntityManager";
	private static final String ENTITY_MANAGER_QUALIFIER = "businessEntityManager";
	private static final String ENTITY_MANAGER_FACTORY_NAME = "businessEntityManagerFactory";
	private static final String ENTITY_MANAGER_FACTORY_QUALIFIER = "businessEntityManagerFactory";
	private static final String ENTITY_PACKAGE_NAME = "com.mzw.business.model";
	private static final String PERSISTENCE_UNIT = "businessPersistenceUnit";
	private static final String TRANSACTION_MANAGER_NAME = "businessTransactionManager";
	private static final String TRANSACTION_MANAGER_QUALIFIER = "businessTransactionManager";

	@Bean(name = DATASOURCE_NAME)
    @Qualifier(DATASOURCE_QUALIFIER)
    @ConfigurationProperties(prefix=CONFIGURATION_PROPERTIES_PREFIX)
	@Primary
    public DataSource businessDataSource() {
        return DataSourceBuilder.create().build();
    }

	@Bean(name = JDBC_NAME)
    public JdbcTemplate businessJdbcTemplate(
            @Qualifier(DATASOURCE_QUALIFIER) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = ENTITY_MANAGER_NAME)
    @Qualifier(ENTITY_MANAGER_QUALIFIER)
    @Primary
    public EntityManager businessEntityManager(EntityManagerFactoryBuilder builder) {
        return businessEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = ENTITY_MANAGER_FACTORY_NAME)
    @Qualifier(ENTITY_MANAGER_FACTORY_QUALIFIER)
    @Primary
    public LocalContainerEntityManagerFactoryBean businessEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(businessDataSource())
                .properties(getVendorProperties(businessDataSource()))
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
    public PlatformTransactionManager businessTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(businessEntityManagerFactory(builder).getObject());
    }
}