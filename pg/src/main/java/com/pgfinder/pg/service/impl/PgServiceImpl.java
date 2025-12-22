package com.pgfinder.pg.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pgfinder.pg.dto.PgDetailsResponse;
import com.pgfinder.pg.dto.PgImageResponse;
import com.pgfinder.pg.dto.PgListResponse;
import com.pgfinder.pg.entity.PG;
import com.pgfinder.pg.entity.PgImage;
import com.pgfinder.pg.repository.PgImageRepository;
import com.pgfinder.pg.repository.PgRepository;
import com.pgfinder.pg.service.PgService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PgServiceImpl implements PgService {

    private final PgRepository pgRepository;
    private final PgImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public PgDetailsResponse getPgDetails(Long pgId) {
        PG pg = pgRepository.findById(pgId)
            .orElseThrow(() -> new RuntimeException("PG not found"));

        List<PgImage> images = imageRepository.findByPgIdOrderBySortOrderAsc(pgId);

        PgDetailsResponse response = new PgDetailsResponse();

        response.setId(pg.getId());
        response.setName(pg.getName());
        response.setAddress(pg.getAddress());
        response.setCity(pg.getCity());
        response.setArea(pg.getArea());
        response.setState(pg.getState());
        response.setPincode(pg.getPincode());

        response.setGender(pg.getGender().name());

        response.setLatitude(pg.getLatitude());
        response.setLongitude(pg.getLongitude());

        response.setPricePerMonth(pg.getPricePerMonth());
        response.setShortStayEnabled(pg.getShortStayEnabled());
        response.setPricePerDay(pg.getPricePerDay());

        response.setTotalBeds(pg.getTotalBeds());
        response.setAvailableBeds(pg.getAvailableBeds());

        response.setWifi(pg.getWifi());
        response.setFood(pg.getFood());
        response.setLaundry(pg.getLaundry());
        response.setParking(pg.getParking());
        response.setAc(pg.getAc());

        response.setStatus(pg.getStatus().name());

        response.setImages(
            images.stream().map(img -> {
                PgImageResponse imgDto = new PgImageResponse();
                imgDto.setId(img.getId());
                imgDto.setImageUrl(img.getImageUrl());
                imgDto.setIsPrimary(img.getIsPrimary());
                imgDto.setSortOrder(img.getSortOrder());
                return imgDto;
            }).toList()
        );

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PgListResponse> getAllPgs(int page, int size) {
        Page<PG> pgPage = pgRepository.findAll(PageRequest.of(page, size));
        
        // Get all PG IDs from current page
        List<Long> pgIds = pgPage.getContent().stream()
            .map(PG::getId)
            .collect(Collectors.toList());
        
        // Fetch ALL images for all PGs in the page
        List<PgImage> allImages = imageRepository.findByPgIdInOrderBySortOrderAsc(pgIds);
        
        // Group images by PG ID
        Map<Long, List<PgImage>> imagesByPgId = allImages.stream()
            .collect(Collectors.groupingBy(PgImage::getPgId));
        
        return pgPage.map(pg -> convertToPgListResponse(pg, imagesByPgId.get(pg.getId())));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<PgListResponse> getPgsByCity(String city, int page, int size) {
        Page<PG> pgPage = pgRepository.findByCityIgnoreCase(city, PageRequest.of(page, size));
        
        // Get all PG IDs from current page
        List<Long> pgIds = pgPage.getContent().stream()
            .map(PG::getId)
            .collect(Collectors.toList());
        
        // Fetch ALL images for all PGs in the page
        List<PgImage> allImages = imageRepository.findByPgIdInOrderBySortOrderAsc(pgIds);
        
        // Group images by PG ID
        Map<Long, List<PgImage>> imagesByPgId = allImages.stream()
            .collect(Collectors.groupingBy(PgImage::getPgId));
        
        return pgPage.map(pg -> convertToPgListResponse(pg, imagesByPgId.get(pg.getId())));
    }
    
    private PgListResponse convertToPgListResponse(PG pg, List<PgImage> images) {
        PgListResponse dto = new PgListResponse();
        
        // Basic info
        dto.setId(pg.getId());
        dto.setName(pg.getName());
        dto.setCity(pg.getCity());
        dto.setArea(pg.getArea());
        dto.setGender(pg.getGender().name());
        
        // Pricing
        dto.setPricePerMonth(pg.getPricePerMonth());
        dto.setShortStayEnabled(pg.getShortStayEnabled());
        dto.setPricePerDay(pg.getPricePerDay());
        
        // Availability
        dto.setTotalBeds(pg.getTotalBeds());
        dto.setAvailableBeds(pg.getAvailableBeds());
        dto.setStatus(pg.getStatus().name());
        
        // Set all images
        if (images != null && !images.isEmpty()) {
            List<PgImageResponse> imageResponses = images.stream()
                .map(img -> {
                    PgImageResponse imgDto = new PgImageResponse();
                    imgDto.setId(img.getId());
                    imgDto.setImageUrl(img.getImageUrl());
                    imgDto.setIsPrimary(img.getIsPrimary());
                    imgDto.setSortOrder(img.getSortOrder());
                    return imgDto;
                })
                .collect(Collectors.toList());
            dto.setImages(imageResponses);
            
            // Set primary image URL for backward compatibility
            images.stream()
                .filter(PgImage::getIsPrimary)
                .findFirst()
                .ifPresentOrElse(
                    primaryImage -> dto.setPrimaryImageUrl(primaryImage.getImageUrl()),
                    () -> dto.setPrimaryImageUrl(images.get(0).getImageUrl())
                );
        }
        
        // Set amenities
        dto.setWifi(pg.getWifi());
        dto.setFood(pg.getFood());
        dto.setLaundry(pg.getLaundry());
        dto.setParking(pg.getParking());
        dto.setAc(pg.getAc());
        
        return dto;
    }
}