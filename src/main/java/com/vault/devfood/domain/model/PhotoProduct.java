package com.vault.devfood.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class PhotoProduct extends BaseEntity{

    private String filename;

    private String description;

    @Column(name = "content_type")
    private String contentType;

    private Long size;
}
