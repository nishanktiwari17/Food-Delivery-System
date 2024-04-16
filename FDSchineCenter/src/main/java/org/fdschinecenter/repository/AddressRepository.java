package org.fdschinecenter.repository;

import org.fdschinecenter.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Address entity.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
