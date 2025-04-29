package org.ii02735.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    @Bean
    public Flyway companyFlyway(DataSource companyDataSource) {
        return Flyway.configure()
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .dataSource(companyDataSource)
                .load();
    }

    @Bean
    public Flyway libraryFlyway(DataSource libraryDataSource) {
        return Flyway.configure()
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .dataSource(libraryDataSource)
                .load();
    }
}
