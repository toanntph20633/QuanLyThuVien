package com.example.quanlythuvien.entities.base;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class CommonDTO {
    private UUID id;

    private int statusLive;

    private int statusAction;

    private LocalDate dateCreate;

    private LocalDate dateUpdate;
}
