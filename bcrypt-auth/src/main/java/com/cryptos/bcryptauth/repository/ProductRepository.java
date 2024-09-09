package com.cryptos.bcryptauth.repository;

import com.cryptos.bcryptauth.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
