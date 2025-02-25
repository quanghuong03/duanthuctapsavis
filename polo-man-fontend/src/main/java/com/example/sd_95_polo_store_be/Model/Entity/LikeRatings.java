package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LikeRatings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer likecount;

    private Integer dislikecount;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "RateId")
    private Ratings ratings;

}
