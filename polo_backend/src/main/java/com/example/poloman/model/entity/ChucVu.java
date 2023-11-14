package com.example.poloman.model.entity;

import com.example.poloman.model.roleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "chucvu")
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer machucvu;
    @Enumerated(EnumType.STRING)
    @NaturalId
    private roleName tenchucvu;
}
