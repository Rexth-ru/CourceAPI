package com.example.easyauction.dto;

import com.example.easyauction.model.Lot;
import com.example.easyauction.prijection.BidNameDate;
import lombok.Data;

@Data
public class FullLot {
    private Integer id;
    private String status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private Integer currentPrice;
    private BidNameDate lastBid;

    public static FullLot lotToFullLot(Lot lot) {
        FullLot fullLot = new FullLot();
        fullLot.setId(lot.getId());
        fullLot.setStatus(lot.getStatus().name());
        fullLot.setTitle(lot.getTitle());
        fullLot.setDescription(lot.getDescription());
        fullLot.setStartPrice(lot.getStartPrice());
        fullLot.setBidPrice(lot.getBidPrice());
        return fullLot;
    }
}
