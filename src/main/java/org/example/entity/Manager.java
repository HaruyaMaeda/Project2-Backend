package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long manager_id;
    @Column
    private String manager_name;
    @Column
    private int years_of_experience;

    public Manager(String manager_name, int years_of_experience) {
        this.manager_name = manager_name;
        this.years_of_experience = years_of_experience;
    }
}
