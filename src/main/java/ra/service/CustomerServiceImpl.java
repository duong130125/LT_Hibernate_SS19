package ra.service;

import org.springframework.stereotype.Service;
import ra.entity.Customer;
import ra.repository.CustomerRepo;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepo.findById(id);
    }

    @Override
    public boolean save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerRepo.update(customer);
    }

    @Override
    public boolean delete(int id) {
        return customerRepo.delete(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        return customerRepo.findByName(name);
    }
}
