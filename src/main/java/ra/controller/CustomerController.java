package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.entity.Customer;
import ra.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

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
        model.addAttribute("customer", new Customer());
        return "customer_add";
    }

    @PostMapping("save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer_edit";
    }

    @PostMapping("update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.update(customer);
        return "redirect:/customer";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("search")
    public String searchCustomer(@RequestParam("keyword") String keyword, Model model) {
        List<Customer> customers = customerService.findByName(keyword);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);
        return "customer_list";
    }

}
