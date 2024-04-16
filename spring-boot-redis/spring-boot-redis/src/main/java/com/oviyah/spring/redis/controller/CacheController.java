package com.oviyah.spring.redis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oviyah.spring.redis.model.Cache;
import com.oviyah.spring.redis.service.CacheService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CacheController {

  @Autowired
  CacheService CacheService;

  @GetMapping("/Caches")
  public ResponseEntity<List<Cache>> getAllCaches(@RequestParam(required = false) String title) {
    try {
      List<Cache> Caches = new ArrayList<Cache>();

      if (title == null)
        CacheService.findAll().forEach(Caches::add);
      else
        CacheService.findByTitleContaining(title).forEach(Caches::add);

      if (Caches.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(Caches, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/Caches/{id}")
  public ResponseEntity<Cache> getCacheById(@PathVariable("id") long id) {
    Optional<Cache> CacheData = CacheService.findById(id);

    if (CacheData.isPresent()) {
      return new ResponseEntity<>(CacheData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Caches")
  public ResponseEntity<Cache> createCache(@RequestBody Cache Cache) {
    try {
      Cache _Cache = CacheService.save(new Cache(Cache.getTitle(), Cache.getDescription(), false));
      return new ResponseEntity<>(_Cache, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/Caches/{id}")
  public ResponseEntity<Cache> updateCache(@PathVariable("id") long id, @RequestBody Cache Cache) {
    Optional<Cache> CacheData = CacheService.findById(id);

    if (CacheData.isPresent()) {
      Cache _Cache = CacheData.get();
      _Cache.setTitle(Cache.getTitle());
      _Cache.setDescription(Cache.getDescription());
      _Cache.setPublished(Cache.isPublished());
      return new ResponseEntity<>(CacheService.update(_Cache), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Caches/{id}")
  public ResponseEntity<HttpStatus> deleteCache(@PathVariable("id") long id) {
    try {
      CacheService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/Caches")
  public ResponseEntity<HttpStatus> deleteAllCaches() {
    try {
      CacheService.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/Caches/published")
  public ResponseEntity<List<Cache>> findByPublished() {
    try {
      List<Cache> Caches = CacheService.findByPublished(true);

      if (Caches.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(Caches, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
