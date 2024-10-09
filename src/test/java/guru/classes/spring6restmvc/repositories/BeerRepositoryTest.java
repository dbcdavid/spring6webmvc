package guru.classes.spring6restmvc.repositories;

import guru.classes.spring6restmvc.bootstrap.BootstrapData;
import guru.classes.spring6restmvc.entities.Beer;
import guru.classes.spring6restmvc.model.BeerStyle;
import guru.classes.spring6restmvc.services.BeerCSVServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({BootstrapData.class, BeerCSVServiceImpl.class})
public class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerListByName(){
        Page<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%", null);

        assertThat(list.getContent().size()).isEqualTo(336);
    }

    @Test
    void testGetBeerListByStyle(){
        Page<Beer> list = beerRepository.findAllByBeerStyle(BeerStyle.STOUT, null);

        assertThat(list.getContent().size()).isEqualTo(57);
    }

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
