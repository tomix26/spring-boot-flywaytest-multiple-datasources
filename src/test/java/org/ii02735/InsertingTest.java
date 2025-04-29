package org.ii02735;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InsertingTest {

    @Test
    void contextLoads(@Autowired Flyway companyFlyway) {
        companyFlyway.migrate();
        assert true;
    }
}
