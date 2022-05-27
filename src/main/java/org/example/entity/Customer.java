package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
//Using this annotation causing the ID to not properly generate @Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String email;
    @Column
    private boolean banned;

    public Customer(String username, String password, String first_name, String last_name, String email, boolean banned){
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.banned = banned;
    }

    public Customer(String username, String password){
        this.username = username;
        this.password = password;
    }


}
