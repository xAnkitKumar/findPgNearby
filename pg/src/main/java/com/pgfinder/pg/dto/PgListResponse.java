package com.pgfinder.pg.dto;

import java.util.List;

import lombok.Data;

@Data
public class PgListResponse {

    private Long id;
    private String name;
    private String city;
    private String area;
    private String gender;

    private Double pricePerMonth;
    private Boolean shortStayEnabled;
    private Double pricePerDay;

    private Integer totalBeds;
    private Integer availableBeds;
    private String status;
    
    // Change from single image to list of images
    private List<PgImageResponse> images;
    
    // Keep these for backward compatibility
    private String primaryImageUrl;
    
    private Boolean wifi;
    private Boolean food;
    private Boolean laundry;
    private Boolean parking;
    private Boolean ac;
}