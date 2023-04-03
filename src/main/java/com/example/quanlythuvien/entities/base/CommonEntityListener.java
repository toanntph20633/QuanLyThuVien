package com.example.quanlythuvien.entities.base;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;

public class CommonEntityListener {
    @PrePersist
    private void prePersistCreate(CommonEntity entity) {
        entity.setDateCreate(LocalDate.now());
    }

    @PreUpdate
    private void prePersistUpdate(CommonEntity entity) {
        entity.setDateUpdate(LocalDate.now());
    }
}
