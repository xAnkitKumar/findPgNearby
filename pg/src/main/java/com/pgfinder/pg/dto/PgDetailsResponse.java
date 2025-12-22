package com.pgfinder.pg.dto;

import java.util.List;

import lombok.Data;

@Data
public class PgDetailsResponse {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String area;
    private String state;
    private String pincode;

    private String gender;

    private Double latitude;
    private Double longitude;

    private Double pricePerMonth;
    private Boolean shortStayEnabled;
    private Double pricePerDay;

    private Integer totalBeds;
    private Integer availableBeds;

    private Boolean wifi;
    private Boolean food;
    private Boolean laundry;
    private Boolean parking;
    private Boolean ac;

    private String status;

    private List<PgImageResponse> images;
}
