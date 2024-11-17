package com.example.demo.repository;

import com.example.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
    // Здесь можно добавлять дополнительные методы поиска, если нужно
}
