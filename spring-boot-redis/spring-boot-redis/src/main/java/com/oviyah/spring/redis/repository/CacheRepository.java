package com.oviyah.spring.redis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oviyah.spring.redis.model.Cache;

@Repository
public interface TutorialRepository extends JpaRepository<Cache, Long> {
  List<Cache> findByPublished(boolean published);

  List<Cache> findByTitleContaining(String title);
}
