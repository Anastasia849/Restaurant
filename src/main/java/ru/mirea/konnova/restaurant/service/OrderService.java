package ru.mirea.konnova.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.mirea.konnova.restaurant.dao.DishDAO;
import ru.mirea.konnova.restaurant.dao.ElementOfOrderDAO;
import ru.mirea.konnova.restaurant.dao.OrderDAO;
import ru.mirea.konnova.restaurant.dto.OrderDTO;
import ru.mirea.konnova.restaurant.model.Dish;
import ru.mirea.konnova.restaurant.model.ElementOfOrder;
import ru.mirea.konnova.restaurant.model.Order;
import ru.mirea.konnova.restaurant.model.User;

import java.util.ArrayList;
import java.util.List;

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

    public List<Dish> getDishes(){
        return dishDAO.findAll();
    }

    public void addElementToOrder(String name){
        ElementOfOrder elementOfOrder=new ElementOfOrder(dishDAO.findByName(name));
        List <ElementOfOrder> elementOfOrderList =new ArrayList<>();


    }



}
