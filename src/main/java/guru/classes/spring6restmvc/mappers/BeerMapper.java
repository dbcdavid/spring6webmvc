package guru.classes.spring6restmvc.mappers;

import guru.classes.spring6restmvc.entities.Beer;
import guru.classes.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDTOToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDTO(Beer beer);
}
