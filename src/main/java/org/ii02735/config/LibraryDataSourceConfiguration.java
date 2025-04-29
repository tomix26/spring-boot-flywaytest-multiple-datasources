package org.ii02735.config;

import org.ii02735.entity.library.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "org.ii02735.repository.library",
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

    @Bean
    public LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory(
            @Qualifier("libraryDataSource") DataSource libraryDataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(libraryDataSource)
                .packages(Book.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager libraryTransactionManager(
            @Qualifier("libraryEntityManagerFactory") LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(libraryEntityManagerFactory.getObject()));
    }
}
