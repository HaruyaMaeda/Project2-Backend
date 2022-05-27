package org.example.services;

import org.example.dao.ManagerRepository;
import org.example.entity.Employee;
import org.example.entity.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class ManagerServiceTest {

    @Mock
    ManagerRepository manager_repository;

    @InjectMocks
    ManagerService manager_service;

    Manager test_manager1;
    Manager test_manager2;

    @BeforeEach
    public void setup(){
        test_manager1 = new Manager(1, "tom", 1);
        test_manager2 = new Manager(2, "mat", 2);
    }

    @Test
    void add_manager_test() {
        Manager savedManager1 = manager_service.add_manager(test_manager1);

        assertThat(savedManager1).isNotNull();
    }

    @Test
    void get_all_managers_test() {
        manager_service.add_manager(test_manager1);
        manager_service.add_manager(test_manager2);

        given(manager_repository.findAll()).willReturn(List.of(test_manager1,test_manager2));

        List<Manager> manager_list = manager_service.get_all_managers();

        assertThat(manager_list).isNotNull();
        assertThat(manager_list.size()).isEqualTo(2);
    }

    @Test
    void get_manager_by_id_test() {
        manager_service.add_manager(test_manager1);

        given(manager_repository.getById(test_manager1.getManager_id())).willReturn(test_manager1);

        assertThat(manager_service.get_manager_by_id(test_manager1.getManager_id())).isEqualTo(test_manager1);
    }

    @Test
    void delete_manager_test() {
        manager_service.add_manager(test_manager1);

        manager_service.delete_manager(test_manager1.getManager_id());

        Manager deleted_manager = manager_service.get_manager_by_id(test_manager1.getManager_id());
        assertThat(deleted_manager).isNull();
    }

    @Test
    void update_manager() {
        manager_service.add_manager(test_manager1);

        given(manager_repository.getById(1L)).willReturn(test_manager1);

        Manager new_manager = new Manager(1, "dave", 1000);
        manager_service.update_manager(new_manager,1L);

        assertThat(manager_service.get_manager_by_id(1L)).isEqualTo(new_manager);
    }
}