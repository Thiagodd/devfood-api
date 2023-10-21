package com.vault.devfood.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cuisine")
public class Cuisine extends BaseEntityAudit{

    private String name;

    @OneToMany(mappedBy = "cuisine", fetch = FetchType.EAGER)
    private List<Restaurant> restaurants = new ArrayList<>();

}
