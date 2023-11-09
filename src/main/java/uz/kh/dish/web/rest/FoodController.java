package uz.kh.dish.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.kh.dish.constants.AuthoritiesConstants;
import uz.kh.dish.domain.Food;
import uz.kh.dish.repository.FoodRepository;
import uz.kh.dish.service.FoodService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    private final FoodRepository foodRepository;

    @GetMapping
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.WAITER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<List<Food>> getAll() {
        return ResponseEntity.ok(foodService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.WAITER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Food> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(foodService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.WAITER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Food> create(@RequestBody Food food) throws Exception {
        if (food.getId() != null) {
            throw new Exception("A new Food cannot already have an id");
        }

        return ResponseEntity.ok(foodService.create(food));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.WAITER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Food> update(@PathVariable("id") Integer id, @RequestBody Food food) throws Exception {
        if (food.getId() == null || !Objects.equals(id, food.getId())) {
            throw new Exception("Invalid id");
        }

        if (!foodRepository.existsById(id)) {
            throw new Exception("Object by given id not found");
        }

        return ResponseEntity.ok(foodService.update(food));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.WAITER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        if (id == null) {
            throw new Exception();
        }
        foodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
