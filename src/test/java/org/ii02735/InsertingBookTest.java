package org.ii02735;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.flyway.OptimizedFlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.FlywayTestExtension;
import org.ii02735.repository.library.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(beanName = "libraryDataSource")
@FlywayTest(flywayName = "libraryFlyway")
class InsertingBookTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testGetFirstBook() {
        assertThat(bookRepository.findFirstByName("my book")).isNotNull();
    }
}
