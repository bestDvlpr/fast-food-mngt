package uz.kh.dish.service;

import uz.kh.dish.domain.Food;

import java.util.List;

public interface FoodService {
    Food create(Food food);

    List<Food> getAll();

    Food getById(Integer id);

    Food update(Food food);

    void delete(Integer id);
}
