package ru.mirea.konnova.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.konnova.restaurant.model.ShoppingCart;
import ru.mirea.konnova.restaurant.model.Status;
import ru.mirea.konnova.restaurant.model.User;


@Repository
public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findByUser(User user);
    ShoppingCart findByStatusesAndAndUser(Status status,User user);
}
