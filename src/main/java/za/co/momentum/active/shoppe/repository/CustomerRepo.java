package za.co.momentum.active.shoppe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.momentum.active.shoppe.entity.Customer;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer, Integer> {
        }
