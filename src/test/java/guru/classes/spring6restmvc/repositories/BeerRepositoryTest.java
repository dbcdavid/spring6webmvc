package guru.classes.spring6restmvc.repositories;

import guru.classes.spring6restmvc.entities.Beer;
import guru.classes.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong(){

        assertThrows(ConstraintViolationException.class, () -> {
            beerRepository.save(Beer.builder()
                    .beerName("TestBeerTestBeerTestBeerTestBeerTestBeerTestBeerTestBeer")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .price(new BigDecimal(11.99))
                    .upc("123456")
                    .build());

            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("Test Beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .price(new BigDecimal(11.99))
                        .upc("123456")
                        .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
        assertThat(savedBeer.getBeerName()).isEqualTo("Test Beer");
    }
}
