package uz.kh.dish.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.kh.dish.constants.AuthoritiesConstants;
import uz.kh.dish.domain.FoodOrder;
import uz.kh.dish.repository.FoodOrderRepository;
import uz.kh.dish.service.FoodOrderService;
import uz.kh.dish.service.dto.FoodOrderDto;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/food-order")
@RequiredArgsConstructor
public class FoodOrderController {
    private final FoodOrderService foodOrderService;
    private final FoodOrderRepository foodOrderRepository;

    @GetMapping
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.WAITER + "\",\"" + AuthoritiesConstants.USER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<List<FoodOrder>> getAll() {
        return ResponseEntity.ok(foodOrderService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.WAITER + "\",\"" + AuthoritiesConstants.USER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<FoodOrder> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(foodOrderService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.USER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<FoodOrder> create(@RequestBody FoodOrderDto foodOrder) throws Exception {
        if (foodOrder.getId() != null) {
            throw new Exception("A new Food cannot already have an id");
        }

        return ResponseEntity.ok(foodOrderService.create(foodOrder));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.USER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<FoodOrder> update(@PathVariable("id") Integer id, @RequestBody FoodOrderDto foodOrder) throws Exception {
        if (foodOrder.getId() == null || !Objects.equals(id, foodOrder.getId())) {
            throw new Exception("Invalid id of order");
        }

        if (!foodOrderRepository.existsById(id)) {
            throw new Exception("Object by given id not found");
        }

        return ResponseEntity.ok(foodOrderService.update(foodOrder));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthoritiesConstants.USER + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        if (id == null) {
            throw new Exception();
        }
        foodOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
