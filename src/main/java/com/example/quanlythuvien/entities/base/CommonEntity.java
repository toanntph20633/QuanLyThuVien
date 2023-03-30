package com.example.quanlythuvien.entities.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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
