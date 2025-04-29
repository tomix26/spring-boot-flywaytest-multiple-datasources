package org.ii02735.config;

import javax.sql.DataSource;

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
    basePackageClasses = Book.class,
    entityManagerFactoryRef = "libraryEntityManagerFactory",
    transactionManagerRef = "libraryTransactionManager"
)
public class LibraryDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.library")
    public DataSourceProperties libraryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.library.hikari")
    public DataSource libraryDataSource() {
        return libraryDataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
    }
}
