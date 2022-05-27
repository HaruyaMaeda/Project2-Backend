package org.example.entity;
import org.example.entity.menu.Sandwich;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Data
@ToString
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long cart_id;
    private long customer_id;
    private long employee_id;
    private double cost_sum;
    private Timestamp cart_date;

    @OneToMany(targetEntity = Sandwich.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_fk", referencedColumnName = "cart_id")
    private Set<Sandwich> sandwiches;


    public Cart(long customer_id, long employee_id, double cost_sum) {
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.cost_sum = cost_sum;
        this.cart_date = new Timestamp(System.currentTimeMillis());
        System.out.println(cart_date);
        this.sandwiches = new HashSet<>();
    }

    public Cart(long cart_id, long customer_id, long employee_id, double cost_sum, Timestamp cart_date) {
        this.cart_id = cart_id;
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.cost_sum = cost_sum;
        this.cart_date = cart_date;
        this.sandwiches = new HashSet<>();
    }

    public Cart() {this.sandwiches = new HashSet<>();
        this.cart_date = new Timestamp(System.currentTimeMillis());
        System.out.println(cart_date);}


    public void addSandwich(Sandwich sandwich) {this.sandwiches.add(sandwich);}
    public void removeSandwich(Sandwich sandwich) {this.sandwiches.remove(sandwich);}
}
