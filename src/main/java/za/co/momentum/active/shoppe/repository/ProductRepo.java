package za.co.momentum.active.shoppe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.momentum.active.shoppe.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
