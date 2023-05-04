package com.example.easyauction.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "bidder_name")
    private String bidderName;
    @Column(name = "bid_date")
    private LocalDateTime bidDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    public LocalDateTime getBidDate() {
        bidDate = LocalDateTime.now();
        return bidDate;
    }
}
