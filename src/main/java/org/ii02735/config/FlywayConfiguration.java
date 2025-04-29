package org.ii02735.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    @Value("${spring.flyway.clean-disabled:true}")
    private boolean cleanDisabled;

    @Bean
    public Flyway companyFlyway(@Qualifier("companyDataSource") DataSource companyDataSource) {
        return Flyway.configure()
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .cleanDisabled(cleanDisabled)
                .locations("db/migration/company")
                .dataSource(companyDataSource)
                .load();
    }

    @Bean
    public Flyway libraryFlyway(@Qualifier("libraryDataSource") DataSource libraryDataSource) {
        return Flyway.configure()
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .cleanDisabled(cleanDisabled)
                .locations("db/migration/library")
                .dataSource(libraryDataSource)
                .load();
    }
}
