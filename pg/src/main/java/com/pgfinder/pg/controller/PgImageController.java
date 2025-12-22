package com.pgfinder.pg.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pgfinder.pg.entity.PgImage;
import com.pgfinder.pg.service.impl.PgImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pgs")
@RequiredArgsConstructor
public class PgImageController {

    private final PgImageService service;

    @PostMapping(
        value = "/{pgId}/images",
        consumes = "multipart/form-data"
    )
    public PgImage uploadImage(
            @PathVariable Long pgId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "false") Boolean isPrimary
    ) {
        return service.uploadPgImage(pgId, file, isPrimary);
    }

    @GetMapping("/{pgId}/images")
    public List<PgImage> getImages(@PathVariable Long pgId) {
        return service.getPgImages(pgId);
    }
}
