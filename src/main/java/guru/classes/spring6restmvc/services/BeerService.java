package guru.classes.spring6restmvc.services;

import guru.classes.spring6restmvc.model.BeerDTO;
import guru.classes.spring6restmvc.model.BeerStyle;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

public interface BeerService {

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> patchBeerById(UUID id, BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer);

    Boolean deleteBeerById(UUID id);
}
