package com.example.quanlythuvien.entities.base;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(CommonEntityListener.class)
@SuperBuilder
public abstract class CommonEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;
    @Column(name = "status_live")
    private int statusLive;
    @Column(name = "status_action")
    private int statusAction;
    @Column(name = "create_date")
    private LocalDate dateCreate;
    @Column(name = "update_date")
    private LocalDate dateUpdate;


}
