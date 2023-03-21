package com.example.easyauction.dto;

import com.example.easyauction.en.Status;
import com.example.easyauction.model.Bid;
import com.example.easyauction.model.Lot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;

@Data
public class LotDTO {
    private Integer id;
    private String status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    @JsonIgnore
    private Collection<Bid> bid;
    @JsonIgnore
    private Integer bidId;
    public static LotDTO lotToDTO(Lot lot){
        LotDTO lotDTO = new LotDTO();
        lotDTO.setId(lot.getId());
        lotDTO.setStatus(lot.getStatus().name());
        lotDTO.setTitle(lot.getTitle());
        lotDTO.setDescription(lot.getDescription());
        lotDTO.setStartPrice(lot.getStartPrice());
        lotDTO.setBidPrice(lot.getBidPrice());
        return lotDTO;
    }
    public Lot toLot(){
        Lot lot = new Lot();
        lot.setId(this.getId());
        lot.setStatus(Status.valueOf(this.getStatus()));
        lot.setTitle(this.getTitle());
        lot.setDescription(this.getDescription());
        lot.setStartPrice(this.getStartPrice());
        lot.setBidPrice(this.getBidPrice());
        return lot;
    }
    public CreatLot toCreateLot(){
        CreatLot creatLot = new CreatLot();
        creatLot.setTitle(this.getTitle());
        creatLot.setDescription(this.getDescription());
        creatLot.setStartPrice(this.getStartPrice());
        creatLot.setBidPrice(this.getBidPrice());
        return creatLot;
    }
    public LotDTO createLotToDTO(CreatLot creatLot){
        LotDTO lotDTO = new LotDTO();
        lotDTO.setId(this.getId());
        lotDTO.setStatus(this.getStatus());
        lotDTO.setTitle(creatLot.getTitle());
        lotDTO.setDescription(creatLot.getDescription());
        lotDTO.setStartPrice(creatLot.getStartPrice());
        lotDTO.setBidPrice(creatLot.getBidPrice());
        return lotDTO;
    }
}
