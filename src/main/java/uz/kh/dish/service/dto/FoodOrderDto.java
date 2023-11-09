package uz.kh.dish.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.kh.dish.domain.Food;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrderDto {
    private Integer id;
    private List<Food> foods;
    private Integer distance; //in kilometers
}
