package org.churchbooks.churchbooks.transactions.services;

import com.webcohesion.ofx4j.io.OFXParseException;
import org.churchbooks.churchbooks.transactions.entities.Transactions;
import org.churchbooks.churchbooks.transactions.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
@Testcontainers
class TransactionServiceImplTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    void save() throws URISyntaxException, IOException, OFXParseException {
        URL url = new URI("https://raw.githubusercontent.com/ftomassetti/ofx-java/master/src/test/resources/example.ofx").toURL();

        transactionRepository.deleteAll();
        transactionService.save(url);

        Assertions.assertEquals(10, transactionRepository.findAll().size());
    }

    @Test
    void findAll(){
        List<Transactions> transactions = transactionService.findAll();
        assertThat(transactions).isNotNull();
    }
}