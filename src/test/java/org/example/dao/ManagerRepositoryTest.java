package org.example.dao;

import org.example.entity.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ManagerRepositoryTest {
    @Autowired
    ManagerRepository manager_repository;

    Manager test_manager1;
    Manager test_manager2;

    @BeforeEach
    public void setup(){
        test_manager1 = new Manager(1, "tom", 1);
        test_manager2 = new Manager(2, "mat", 2);
    }

    @Test
    public void injectedComponentsAreNotNull(){
        Manager savedManager = manager_repository.save(test_manager1);

        assertThat(savedManager).isNotNull();
    }

    @Test
    public void findAllTest(){
        manager_repository.save(test_manager1);
        manager_repository.save(test_manager2);

        List<Manager> managerList = manager_repository.findAll();

        assertThat(managerList).isNotNull();
        assertThat(managerList.size()).isEqualTo(2);
    }

    @Test
    public void findByIdTest(){
        manager_repository.save(test_manager1);

        Manager manager_in_DB = manager_repository.findById(test_manager1.getManager_id()).get();

        assertThat(manager_in_DB).isNotNull();
    }

    @Test
    public void updateTest(){
        manager_repository.save(test_manager1);

        Manager saved_manager = manager_repository.findById(test_manager1.getManager_id()).get();
        saved_manager.setManager_name("new_name");

        Manager updated_manager = manager_repository.save(saved_manager);

        assertThat(updated_manager.getManager_name()).isEqualTo("new_name");

    }

    @Test
    public void deleteTest(){
        manager_repository.save(test_manager1);

        manager_repository.deleteById(test_manager1.getManager_id());

        Optional<Manager> deleted_manager = manager_repository.findById(test_manager1.getManager_id());
        assertThat(deleted_manager).isEmpty();
    }
}