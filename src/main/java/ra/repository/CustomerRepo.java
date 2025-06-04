package ra.repository;

import ra.entity.Customer;

import java.util.List;

public interface CustomerRepo {
    List<Customer> findAll();
    Customer findById(int id);
    boolean save(Customer customer);
    boolean update(Customer customer);
    boolean delete(int id);
    List<Customer> findByName(String name);
}
