package ru.mirea.konnova.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.konnova.restaurant.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByName(String name);
}
