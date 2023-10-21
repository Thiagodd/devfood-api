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
@Table(name = "user")
public class User extends BaseEntityAudit{

    private String name;
    private String email;
    private String password;
    private Boolean active;

    public Boolean isActive() {
        return active;
    }

}
