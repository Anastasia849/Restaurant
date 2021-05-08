package ru.mirea.konnova.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.konnova.restaurant.model.Dish;

@Repository
public interface DishDAO extends JpaRepository<Dish, Integer> {
    Dish findByName(String name);
}
