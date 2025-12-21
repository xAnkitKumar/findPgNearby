package com.pgfinder.pg.controller;

import com.pgfinder.pg.entity.PG;
import com.pgfinder.pg.service.PgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pgs")
public class PgController {

    @Autowired
    private PgService pgService;

    // Create a new PG
    @PostMapping
    public ResponseEntity<PG> createPG(@RequestBody PG pg) {
        try {
            PG createdPG = pgService.createPG(pg);
            return new ResponseEntity<>(createdPG, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get all PGs
    @GetMapping
    public ResponseEntity<List<PG>> getAllPGs() {
        try {
            List<PG> pgs = pgService.getAllPGs();
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get PG by ID
    @GetMapping("/{id}")
    public ResponseEntity<PG> getPGById(@PathVariable Long id) {
        try {
            Optional<PG> pg = pgService.getPGById(id);
            return pg.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get PGs by owner ID
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PG>> getPGsByOwnerId(@PathVariable Long ownerId) {
        try {
            List<PG> pgs = pgService.getPGsByOwnerId(ownerId);
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get PGs by location
    @GetMapping("/location/{location}")
    public ResponseEntity<List<PG>> getPGsByLocation(@PathVariable String location) {
        try {
            List<PG> pgs = pgService.getPGsByLocation(location);
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get PGs by location and budget
    @GetMapping("/search")
    public ResponseEntity<List<PG>> getPGsByLocationAndBudget(
            @RequestParam String location,
            @RequestParam Double minBudget,
            @RequestParam Double maxBudget) {
        try {
            List<PG> pgs = pgService.getPGsByLocationAndBudget(location, minBudget, maxBudget);
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get PGs by budget range
    @GetMapping("/budget")
    public ResponseEntity<List<PG>> getPGsByBudgetRange(
            @RequestParam Double minBudget,
            @RequestParam Double maxBudget) {
        try {
            List<PG> pgs = pgService.getPGsByBudgetRange(minBudget, maxBudget);
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get available PGs
    @GetMapping("/available")
    public ResponseEntity<List<PG>> getAvailablePGs() {
        try {
            List<PG> pgs = pgService.getAvailablePGs();
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get available PGs by location
    @GetMapping("/available/location/{location}")
    public ResponseEntity<List<PG>> getAvailablePGsByLocation(@PathVariable String location) {
        try {
            List<PG> pgs = pgService.getAvailablePGsByLocation(location);
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get PGs by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<PG>> getPGsByCity(@PathVariable String city) {
        try {
            List<PG> pgs = pgService.getPGsByCity(city);
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update PG
    @PutMapping("/{id}")
    public ResponseEntity<PG> updatePG(@PathVariable Long id, @RequestBody PG pgDetails) {
        try {
            PG updatedPG = pgService.updatePG(id, pgDetails);
            return new ResponseEntity<>(updatedPG, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Delete PG
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePG(@PathVariable Long id) {
        try {
            pgService.deletePG(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Search PGs by amenity
    @GetMapping("/amenity/{amenity}")
    public ResponseEntity<List<PG>> searchPGsByAmenity(@PathVariable String amenity) {
        try {
            List<PG> pgs = pgService.searchPGsByAmenity(amenity);
            return new ResponseEntity<>(pgs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Count available PGs
    @GetMapping("/count/available")
    public ResponseEntity<Long> countAvailablePGs() {
        try {
            long count = pgService.countAvailablePGs();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Count PGs by owner
    @GetMapping("/count/owner/{ownerId}")
    public ResponseEntity<Long> countPGsByOwner(@PathVariable Long ownerId) {
        try {
            long count = pgService.countPGsByOwner(ownerId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
