package org.example.services;

import org.example.dao.ComplaintsRepository;
import org.example.entity.Complaints;
import org.example.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class ComplaintsServiceTest {

    @Mock
    ComplaintsRepository complaintsRepository;

    @InjectMocks
    ComplaintsService complaintsService = new ComplaintsService();

    Complaints complaints1;
    Complaints complaints2;

    @BeforeEach
    public void setup(){
        complaints1 = new Complaints(1L,1L,1l,"AHHHHHH", Timestamp.valueOf("2111-01-14 09:01:16"));
        complaints2 = new Complaints(2L,2L,1l,"WAHHH", Timestamp.valueOf("2020-01-14 09:01:16"));
    }



    @Test
    void add_complaints() {
       assertThat(complaintsService.add_complaints(complaints1)).isNotNull();
    }

    @Test
    void get_complaints_by_id() {
        complaintsService.add_complaints(complaints1);

        given(complaintsService.get_complaints_by_id(1L)).willReturn(complaints1);

        assertThat(complaintsService.get_complaints_by_id(1L)).isEqualTo(complaints1);
    }

    @Test
    void get_all_complaints_by_customer_id() {
        complaintsService.add_complaints(complaints1);
        complaintsService.add_complaints(complaints2);

        given(complaintsRepository.getAllByCustomer_id(2l)).willReturn(List.of(complaints2));

        assertThat(complaintsService.get_all_complaints_by_customer_id(2L).size()).isEqualTo(1);
    }

    @Test
    void get_all_complaints() {
        complaintsService.add_complaints(complaints1);
        complaintsService.add_complaints(complaints2);

        given(complaintsRepository.findAll()).willReturn(List.of(complaints1,complaints2));

        List<Complaints> complaintsList = complaintsService.get_all_complaints();

        assertThat(complaintsList).isNotNull();
        assertThat(complaintsList.size()).isEqualTo(2);
    }

    @Test
    void update_complaints() {
        complaintsService.add_complaints(complaints1);
        Complaints complaintsNew = new Complaints(1L,1L,1l,"RAHHHHH", Timestamp.valueOf("1999-01-14 09:01:16"));
        complaintsService.update_complaints(complaintsNew);

        given(complaintsRepository.findByComplaintsId(1L)).willReturn(complaintsNew);

        assertThat(complaintsService.get_complaints_by_id(1L)).isEqualTo(complaintsNew);
    }

    @Test
    void delete_complaints() {
        complaintsService.add_complaints(complaints1);

        complaintsService.delete_complaints(1L);

        Complaints complaints = complaintsService.get_complaints_by_id(1L);
        assertThat(complaints).isNull();
    }
}