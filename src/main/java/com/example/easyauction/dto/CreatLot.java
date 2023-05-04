package com.example.easyauction.dto;

import com.example.easyauction.model.Lot;
import lombok.Data;

@Data
public class CreatLot {
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    public static CreatLot lotToCreatLot(Lot lot) {
        CreatLot creatLot = new CreatLot();
        creatLot.setTitle(lot.getTitle());
        creatLot.setDescription(lot.getDescription());
        creatLot.setStartPrice(lot.getStartPrice());
        creatLot.setBidPrice(creatLot.getBidPrice());
        return creatLot;
    }
    public Lot toLot() {
        Lot lot = new Lot();
        lot.setTitle(this.getTitle());
        lot.setDescription(this.getDescription());
        lot.setStartPrice(this.getStartPrice());
        lot.setBidPrice(this.getBidPrice());
        return lot;
    }
}
