package com.pgfinder.pg.controller;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pgfinder.pg.dto.PgDetailsResponse;
import com.pgfinder.pg.dto.PgListResponse;
import com.pgfinder.pg.service.PgService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pgs")
@RequiredArgsConstructor
public class PgController {

    private final PgService pgService;

    @GetMapping("/{pgId}")
    public PgDetailsResponse getPgDetails(@PathVariable Long pgId) {
        return pgService.getPgDetails(pgId);
    }

    @GetMapping
public Page<PgListResponse> getAllPgs(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    return pgService.getAllPgs(page, size);
}

@GetMapping("/cities/{city}")
public Page<PgListResponse> getPgsByCity(
        @PathVariable String city,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    return pgService.getPgsByCity(city, page, size);
}
}