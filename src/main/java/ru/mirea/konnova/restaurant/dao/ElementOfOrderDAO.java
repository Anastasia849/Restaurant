package ru.mirea.konnova.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.konnova.restaurant.model.ElementOfOrder;
import ru.mirea.konnova.restaurant.model.ShoppingCart;

import java.util.List;


@Repository
public interface ElementOfOrderDAO extends JpaRepository<ElementOfOrder, Integer> {
    List<ElementOfOrder> findByShoppingCart(ShoppingCart shoppingCart);
}
