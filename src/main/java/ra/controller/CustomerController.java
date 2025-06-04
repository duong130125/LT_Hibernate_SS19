package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ra.entity.Customer;
import ra.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customer")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.findAll();

        model.addAttribute("customers", customers);
        return "customer_list";
    }

    @GetMapping("create")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer_add";
    }
}
