package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.entity.Customer;
import ra.service.CustomerService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ✅ Danh sách khách hàng
    @GetMapping("customer")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer_list";
    }

    // ✅ Hiển thị form thêm
    @GetMapping("create")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_add";
    }

    // ✅ Xử lý thêm mới
    @PostMapping("save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer,
                               @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            try {
                imageFile.transferTo(new File("uploads/" + fileName));
                customer.setFileImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customerService.save(customer);
        return "redirect:/customer";
    }

    // ✅ Hiển thị form sửa
    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer_edit";
    }

    // ✅ Xử lý cập nhật
    @PostMapping("update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            try {
                imageFile.transferTo(new File("uploads/" + fileName));
                customer.setFileImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customerService.update(customer);
        return "redirect:/customer";
    }

    // ✅ Xóa khách hàng
    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return "redirect:/customer";
    }
}
