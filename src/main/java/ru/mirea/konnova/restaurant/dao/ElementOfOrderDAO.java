package ru.mirea.konnova.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.konnova.restaurant.model.ElementOfOrder;
import ru.mirea.konnova.restaurant.model.Order;

import javax.transaction.Transactional;

@Repository
public interface ElementOfOrderDAO extends JpaRepository<ElementOfOrder, Integer> {
    @Transactional
    void deleteAllByOrder(Order order);
}