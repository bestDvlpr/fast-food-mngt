package uz.kh.dish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.kh.dish.domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
