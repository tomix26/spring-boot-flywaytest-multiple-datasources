package org.ii02735;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.FlywayTestExtension;
import org.ii02735.repository.company.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(FlywayTestExtension.class)
@FlywayTest(flywayName = "companyFlyway")
class InsertingEmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testGetFirstEmployee() {
        assertThat(employeeRepository.findFirstByName("John")).isNotNull();
    }
}
