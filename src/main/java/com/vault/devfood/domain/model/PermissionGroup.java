package com.vault.devfood.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permission_group")
public class PermissionGroup extends BaseEntityAudit{

    private String name;
}
