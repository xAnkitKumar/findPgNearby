package com.pgfinder.pg.dto;

import lombok.Data;

@Data
public class PgImageResponse {

    private Long id;
    private String imageUrl;
    private Boolean isPrimary;
    private Integer sortOrder;
}
