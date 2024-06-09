package ru.ecomshop.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "couriers")
public class Courier {

    @Id
    private Long id;
}
