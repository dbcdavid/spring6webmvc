package guru.classes.spring6restmvc.mappers;

import guru.classes.spring6restmvc.entities.Customer;
import guru.classes.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
