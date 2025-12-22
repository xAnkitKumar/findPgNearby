package com.pgfinder.pg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pgfinder.pg.entity.PgImage;
import com.pgfinder.pg.repository.PgImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PgImageService {

    private final PgImageRepository repository;
    private final CloudinaryService cloudinaryService;

    public PgImage uploadPgImage(Long pgId, MultipartFile file, Boolean isPrimary) {

        if (Boolean.TRUE.equals(isPrimary)) {
            repository.findByPgIdAndIsPrimaryTrue(pgId)
                .ifPresent(img -> {
                    img.setIsPrimary(false);
                    repository.save(img);
                });
        }

        String imageUrl = cloudinaryService.uploadImage(file, "pgs/" + pgId);

        PgImage image = new PgImage();
        image.setPgId(pgId);
        image.setImageUrl(imageUrl);
        image.setIsPrimary(isPrimary);

        return repository.save(image);
    }

    public List<PgImage> getPgImages(Long pgId) {
        return repository.findByPgIdOrderBySortOrderAsc(pgId);
    }
}

