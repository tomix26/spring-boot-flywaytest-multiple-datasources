package org.ii02735;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.FlywayTestExtension;
import org.ii02735.repository.library.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(FlywayTestExtension.class)
@FlywayTest(flywayName = "libraryFlyway")
class InsertingBookTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testGetFirstBook() {
        assertThat(bookRepository.findFirstByName("my book")).isNotNull();
    }
}
