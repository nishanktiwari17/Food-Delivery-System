package org.fdschinecenter.repository;

import org.fdschinecenter.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for CustomerOrder entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {
    List<CustomerOrder> findByRestaurantId (String restaurantId);
}
