package com.example.quanlythuvien.entities.base;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class CommonDTO {
    private UUID id;
@Column(name = "status_live")
    private int statusLive;
    @Column(name = "status_live")

    private int statusAction;
    @Column(name = "create_date")

    private LocalDate dateCreate;
    @Column(name = "update_date")
    private LocalDate dateUpdate;
}
