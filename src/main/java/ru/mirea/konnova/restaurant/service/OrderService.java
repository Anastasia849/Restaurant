package ru.mirea.konnova.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.konnova.restaurant.dao.DishDAO;
import ru.mirea.konnova.restaurant.dao.ElementOfOrderDAO;
import ru.mirea.konnova.restaurant.dao.ShoppingCartDAO;
import ru.mirea.konnova.restaurant.model.*;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private final DishDAO dishDAO;
    private final ShoppingCartDAO shoppingCartDAO;
    private final ElementOfOrderDAO elementOfOrderDAO;

    @Autowired
    public OrderService(DishDAO dishDAO, ShoppingCartDAO shoppingCartDAO, ElementOfOrderDAO elementOfOrderDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
        this.elementOfOrderDAO = elementOfOrderDAO;
        this.dishDAO = dishDAO;
    }

    public List<Dish> getDishes(){
        return dishDAO.findAll();
    }

    public List<ElementOfOrder> getCart(User user){
        return elementOfOrderDAO.findByShoppingCart(shoppingCartDAO.findByStatusesAndAndUser(Status.CART,user));
    }

    public void addElementToOrder(int id, User user){
        ElementOfOrder elementOfOrder = new ElementOfOrder(dishDAO.findById(id));
        ShoppingCart shoppingCart = shoppingCartDAO.findByStatusesAndAndUser(Status.CART,user);
        elementOfOrder.setShoppingCart(shoppingCart);
        elementOfOrderDAO.save(elementOfOrder);
    }

    public void deleteElementFromOrder(int id){
        elementOfOrderDAO.deleteById(id);
    }

    public float getTotal(List<ElementOfOrder> elementOfOrders){
        float total=0;
        for (ElementOfOrder elementOfOrder : elementOfOrders) {
            total += elementOfOrder.getPrice();
        }
        return total;
    }

    public void confirm(User user){
        ShoppingCart shoppingCart = shoppingCartDAO.findByStatusesAndAndUser(Status.CART,user);
        shoppingCart.setStatuses(Collections.singleton(Status.ORDER_PROCESSING));
        shoppingCartDAO.save(shoppingCart);

        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setStatuses(Collections.singleton(Status.CART));
        newShoppingCart.setUser(user);
        shoppingCartDAO.save(newShoppingCart);
    }

}
