package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Admins extends BaseEntity<Admins> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private String password;
    private Integer status;
    @ManyToOne
    @JoinColumn(name="roleid")
    private Role role;

    @Override
    protected Admins self() {
        return null;
    }
}
