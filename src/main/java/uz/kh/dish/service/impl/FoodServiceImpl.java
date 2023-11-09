package uz.kh.dish.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kh.dish.domain.Food;
import uz.kh.dish.repository.FoodRepository;
import uz.kh.dish.service.FoodService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Food create(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    @Override
    public Food getById(Integer id) {
        return foodRepository.findById(id).orElseThrow();
    }

    @Override
    public Food update(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void delete(Integer id) {
        boolean existsById = foodRepository.existsById(id);
        if (existsById){
            foodRepository.deleteById(id);
        }

        throw new EntityNotFoundException();
    }
}
