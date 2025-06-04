package ra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 100)
    @Size(min = 1, max = 10, message = "First name must be between 1 and 10 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    @Size(min = 1, max = 10, message = "Last name must be between 1 and 10 characters")
    private String lastName;

    private String phone;
    private String address;
    private String email;
    private String fileImage; // đường dẫn tới ảnh đã upload
}

