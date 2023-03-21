package com.example.easyauction.model;

import com.example.easyauction.en.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "start_price")
    private Integer startPrice;
    @Column(name = "bid_price")
    private Integer bidPrice;
    @OneToMany(mappedBy = "lot")
    private Collection<Bid> bid;

}
