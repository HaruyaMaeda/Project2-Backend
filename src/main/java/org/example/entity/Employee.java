package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employee_id;

    @Column
    private long manager_id;
    @Column
    private String employee_name;
    @Column
    private int years_of_experience;

    public Employee(long manager_id, String employee_name, int years_of_experience) {
        this.manager_id = manager_id;
        this.employee_name = employee_name;
        this.years_of_experience = years_of_experience;
    }
}
