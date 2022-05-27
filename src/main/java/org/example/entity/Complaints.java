package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


@AllArgsConstructor
@Data
@ToString
@Entity
public class Complaints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complaints_id;
    private long cart_id;
    private long customer_id;
    private String complaints;
    private Timestamp complaints_date;

    public Complaints(){
        this.complaints_date = new Timestamp(System.currentTimeMillis());

    }

}
