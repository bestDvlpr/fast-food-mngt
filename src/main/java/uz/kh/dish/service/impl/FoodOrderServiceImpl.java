package uz.kh.dish.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.kh.dish.domain.Food;
import uz.kh.dish.domain.FoodOrder;
import uz.kh.dish.repository.FoodOrderRepository;
import uz.kh.dish.service.FoodOrderService;
import uz.kh.dish.service.dto.FoodOrderDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FoodOrderServiceImpl implements FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;
    @Value("${meal.delivery.time}")
    private Integer averageDeliveryTimePerKilometer; // in minutes

    @Override
    public FoodOrder create(FoodOrderDto foodOrderDto) {
        FoodOrder foodOrder = new FoodOrder();
        Set<Food> foods = new HashSet<>(foodOrderDto.getFoods());
        foodOrder.setFoodSet(foods);
        Integer deliveryTime = foodOrderDto.getDistance() * averageDeliveryTimePerKilometer;
        foodOrder.setDeliveryTime(deliveryTime);
        return foodOrderRepository.save(foodOrder);
    }

    @Override
    public List<FoodOrder> getAll() {
        return foodOrderRepository.findAll();
    }

    @Override
    public FoodOrder getById(Integer id) {
        return foodOrderRepository.findById(id).orElseThrow();
    }

    @Override
    public FoodOrder update(FoodOrderDto foodOrderDto) {
        FoodOrder foodOrder = new FoodOrder();
        Set<Food> foods = new HashSet<>(foodOrderDto.getFoods());
        foodOrder.setFoodSet(foods);
        return foodOrderRepository.save(foodOrder);
    }

    @Override
    public void delete(Integer id) {
        boolean existsById = foodOrderRepository.existsById(id);
        if (existsById) {
            foodOrderRepository.deleteById(id);
        }

        throw new EntityNotFoundException();
    }
}
