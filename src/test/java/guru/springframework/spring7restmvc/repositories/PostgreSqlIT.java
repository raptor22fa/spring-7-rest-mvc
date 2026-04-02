package guru.springframework.spring7restmvc.repositories;

import java.util.List;
import guru.springframework.spring7restmvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jt, Spring Framework Guru.
 */
@Testcontainers
@SpringBootTest
@ActiveProfiles("local-postgresql")
public class PostgreSqlIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> mySQLContainer = new PostgreSQLContainer<>( "postgres:15.17");

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeers() {
        List<Beer> beers = beerRepository.findAll();

        assertThat(beers.size()).isGreaterThan(0);
    }
}
