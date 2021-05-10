package ru.mirea.konnova.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.konnova.restaurant.dao.DishDAO;
import ru.mirea.konnova.restaurant.dao.ElementOfOrderDAO;
import ru.mirea.konnova.restaurant.dao.OrderDAO;

@Service
public class OrderService {
    private final OrderDAO orderDAO;
    private final DishDAO dishDAO;
    private final ElementOfOrderDAO elementOfOrderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO, DishDAO dishDAO, ElementOfOrderDAO elementOfOrderDAO) {
        this.orderDAO = orderDAO;
        this.dishDAO = dishDAO;
        this.elementOfOrderDAO = elementOfOrderDAO;
    }

}
