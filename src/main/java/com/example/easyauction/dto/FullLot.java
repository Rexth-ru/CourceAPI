package com.example.easyauction.dto;

import com.example.easyauction.en.Status;
import com.example.easyauction.model.Bid;
import com.example.easyauction.model.Lot;
import lombok.Data;

@Data
public class FullLot {
    private LotDTO lotDTO;
//    private Integer id;
//    private String status;
//    private String title;
//    private String description;
//    private Integer startPrice;
//    private Integer bidPrice;
    private Integer currentPrice;
    private Bid lastBid;

//    public static FullLot lotToFullLot(Lot lot, Bid bid){
//        FullLot fullLot = new FullLot();
//        fullLot.setId(lot.getId());
//        fullLot.setStatus(lot.getStatus().name());
//        fullLot.setTitle(lot.getTitle());
//        fullLot.setDescription(lot.getDescription());
//        fullLot.setStartPrice(lot.getStartPrice());
//        fullLot.setBidPrice(lot.getBidPrice());
//        fullLot.setCurrentPrice()
//        return fullLot;
//    }
//    private Integer currentPrice()

}
