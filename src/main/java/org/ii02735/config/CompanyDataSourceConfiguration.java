package org.ii02735.config;

import org.ii02735.entity.company.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackageClasses = Employee.class,
    entityManagerFactoryRef = "companyEntityManagerFactory",
    transactionManagerRef = "companyTransactionManager"
)
public class CompanyDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.company")
    public DataSourceProperties companyDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.company.hikari")
    @Primary
    public DataSource companyDataSource() {
        return companyDataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean companyEntityManagerFactory(
            @Qualifier("companyDataSource") DataSource companyDataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(companyDataSource)
                .packages(Employee.class)
                .build();
    }
}
