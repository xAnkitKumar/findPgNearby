package com.pgfinder.pg.service.impl;

import com.pgfinder.pg.entity.PG;
import com.pgfinder.pg.repository.PgRepository;
import com.pgfinder.pg.service.PgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PgServiceImpl implements PgService {

    @Autowired
    private PgRepository pgRepository;

    @Override
    public PG createPG(PG pg) {
        pg.setCreatedAt(LocalDateTime.now());
        pg.setUpdatedAt(LocalDateTime.now());
        return pgRepository.save(pg);
    }

    @Override
    public List<PG> getAllPGs() {
        return pgRepository.findAll();
    }

    @Override
    public Optional<PG> getPGById(Long id) {
        return pgRepository.findById(id);
    }

    @Override
    public List<PG> getPGsByOwnerId(Long ownerId) {
        return pgRepository.findByOwnerIdOrderByCreatedAtDesc(ownerId);
    }

    @Override
    public List<PG> getPGsByLocation(String location) {
        return pgRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<PG> getPGsByLocationAndBudget(String location, Double minBudget, Double maxBudget) {
        return pgRepository.findByLocationAndBudget(location, minBudget, maxBudget);
    }

    @Override
    public List<PG> getPGsByBudgetRange(Double minBudget, Double maxBudget) {
        return pgRepository.findByRentBetweenOrderByRentAsc(minBudget, maxBudget);
    }

    @Override
    public List<PG> getAvailablePGs() {
        return pgRepository.findByAvailableTrueOrderByCreatedAtDesc();
    }

    @Override
    public List<PG> getAvailablePGsByLocation(String location) {
        return pgRepository.findByLocationContainingIgnoreCaseAndAvailableTrueOrderByRentAsc(location);
    }

    @Override
    public List<PG> getPGsByCity(String city) {
        return pgRepository.findByLocationContainingIgnoreCase(city);
    }

    @Override
    public PG updatePG(Long id, PG pgDetails) {
        Optional<PG> optionalPG = pgRepository.findById(id);
        if (optionalPG.isPresent()) {
            PG pg = optionalPG.get();
            if (pgDetails.getName() != null) {
                pg.setName(pgDetails.getName());
            }
            if (pgDetails.getAddress() != null) {
                pg.setAddress(pgDetails.getAddress());
            }
            if (pgDetails.getCity() != null) {
                pg.setCity(pgDetails.getCity());
            }
            if (pgDetails.getState() != null) {
                pg.setState(pgDetails.getState());
            }
            if (pgDetails.getPincode() != null) {
                pg.setPincode(pgDetails.getPincode());
            }
            if (pgDetails.getLatitude() != null) {
                pg.setLatitude(pgDetails.getLatitude());
            }
            if (pgDetails.getLongitude() != null) {
                pg.setLongitude(pgDetails.getLongitude());
            }
            if (pgDetails.getPricePerMonth() != null) {
                pg.setPricePerMonth(pgDetails.getPricePerMonth());
            }
            if (pgDetails.getWifi() != null) {
                pg.setWifi(pgDetails.getWifi());
            }
            if (pgDetails.getFood() != null) {
                pg.setFood(pgDetails.getFood());
            }
            if (pgDetails.getLaundry() != null) {
                pg.setLaundry(pgDetails.getLaundry());
            }
            if (pgDetails.getParking() != null) {
                pg.setParking(pgDetails.getParking());
            }
            if (pgDetails.getAc() != null) {
                pg.setAc(pgDetails.getAc());
            }
            pg.setUpdatedAt(LocalDateTime.now());
            return pgRepository.save(pg);
        } else {
            throw new RuntimeException("PG not found with id: " + id);
        }
    }

    @Override
    public void deletePG(Long id) {
        if (!pgRepository.existsById(id)) {
            throw new RuntimeException("PG not found with id: " + id);
        }
        pgRepository.deleteById(id);
    }

    @Override
    public List<PG> searchPGsByAmenity(String amenity) {
        return pgRepository.findByAmenitiesContainingIgnoreCase(amenity);
    }

    @Override
    public long countAvailablePGs() {
        return pgRepository.countByAvailableTrue();
    }

    @Override
    public long countPGsByOwner(Long ownerId) {
        return pgRepository.countByOwnerId(ownerId);
    }

}
