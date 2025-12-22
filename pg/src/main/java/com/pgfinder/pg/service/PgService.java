package com.pgfinder.pg.service;

import org.springframework.data.domain.Page;

import com.pgfinder.pg.dto.PgDetailsResponse;
import com.pgfinder.pg.dto.PgListResponse;

public interface PgService {

    PgDetailsResponse getPgDetails(Long pgId);
    Page<PgListResponse> getAllPgs(int page, int size);
    Page<PgListResponse> getPgsByCity(String city, int page, int size);
}
