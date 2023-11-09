package uz.kh.dish.service;

import uz.kh.dish.domain.FoodOrder;
import uz.kh.dish.service.dto.FoodOrderDto;

import java.util.List;

public interface FoodOrderService {
    FoodOrder create(FoodOrderDto food);

    List<FoodOrder> getAll();

    FoodOrder getById(Integer id);

    FoodOrder update(FoodOrderDto food);

    void delete(Integer id);
}
