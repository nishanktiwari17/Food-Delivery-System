package org.fdschinecenter.repository;

import org.fdschinecenter.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Restaurant entity.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    /**
     * Finds restarts nearby a given location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @param radius The radius in kilometers.
     * @return A list of restaurants nearby the given location.
     */
    @Query(value = "SELECT * FROM restaurant r " +
            "WHERE (6371 * ACOS(" +
            "  COS(RADIANS(:latitude)) * COS(RADIANS(r.latitude)) * COS(RADIANS(r.longitude) - RADIANS(:longitude)) + " +
            "  SIN(RADIANS(:latitude)) * SIN(RADIANS(r.latitude))" +
            ")) <= :radius", nativeQuery = true)
    List<Restaurant> findRestaurantsNearby(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("radius") Double radius
    );

    /**
     * Finds a restaurant by the id of the restaurant admin.
     * @param id The id of the restaurant admin.
     * @return The restaurant with the given restaurant admin id.
     */
    Optional<Restaurant> findRestaurantByRestaurantAdminId(UUID id);

}
