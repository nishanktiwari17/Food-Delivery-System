package org.fdschinecenter.repository;

import org.fdschinecenter.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for FoodMenu entity.
 */
@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, Integer> {

    Optional<FoodMenu> findByIdAndRestaurantId(int id, UUID restaurantId);
    List<FoodMenu> findFoodMenuByRestaurantId(UUID id);
}
