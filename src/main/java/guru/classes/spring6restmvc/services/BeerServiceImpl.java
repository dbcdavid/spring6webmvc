package guru.classes.spring6restmvc.services;

import guru.classes.spring6restmvc.model.BeerDTO;
import guru.classes.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize){
        return new PageImpl<>(new ArrayList<>(beerMap.values()));
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get beer {} in service was called", id);

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .updateDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID id, BeerDTO beer) {
        BeerDTO existingBeer = beerMap.get(id);
        if (existingBeer != null) {
            if (StringUtils.hasText(beer.getBeerName())) {
                existingBeer.setBeerName(beer.getBeerName());
            }

            if (beer.getBeerStyle() != null) {
                existingBeer.setBeerStyle(beer.getBeerStyle());
            }

            if (beer.getPrice() != null) {
                existingBeer.setPrice(beer.getPrice());
            }

            if (beer.getQuantityOnHand() != null) {
                existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
            }

            if (StringUtils.hasText(beer.getUpc())) {
                existingBeer.setUpc(beer.getUpc());
            }

            existingBeer.setUpdateDate(LocalDateTime.now());
            beerMap.put(existingBeer.getId(), existingBeer);
        }

        return Optional.of(existingBeer);
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer) {
        BeerDTO existingBeer = beerMap.get(id);
        if (existingBeer != null) {
            existingBeer.setBeerName(beer.getBeerName());
            existingBeer.setUpc(beer.getUpc());
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
            existingBeer.setPrice(beer.getPrice());
            existingBeer.setUpdateDate(LocalDateTime.now());
            existingBeer.setBeerStyle(beer.getBeerStyle());

            beerMap.put(existingBeer.getId(), existingBeer);
        }
        return Optional.of(existingBeer);
    }

    @Override
    public Boolean deleteBeerById(UUID id) {
        if (beerMap.containsKey(id)) {
            beerMap.remove(id);
            return true;
        }

        return false;
    }
}
