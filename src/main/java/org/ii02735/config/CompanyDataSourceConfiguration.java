package org.ii02735.config;

import javax.sql.DataSource;

import org.ii02735.entity.company.Employee;
import org.ii02735.entity.library.Book;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
    public DataSource companyDataSource() {
        return companyDataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
    }
}
