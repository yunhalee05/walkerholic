package com.yunhalee.walkerholic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "activity")
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Integer id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

    @Embedded
    private Address from;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name" , column = @Column(name = "to_name")),
            @AttributeOverride( name = "country", column = @Column(name = "to_country")),
            @AttributeOverride( name = "city", column = @Column(name = "to_city")),
            @AttributeOverride( name = "zipcode", column = @Column(name = "to_zipcode")),
            @AttributeOverride( name = "address", column = @Column(name = "to_address")),
            @AttributeOverride( name = "latitude", column = @Column(name = "to_latitude")),
            @AttributeOverride( name = "longitude", column = @Column(name = "to_longitude"))
    })
    private Address to;

    private Integer weight;

    private Integer distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
