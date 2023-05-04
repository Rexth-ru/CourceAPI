package com.example.easyauction.dto;
import com.example.easyauction.model.Bid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BidDTO {
    @JsonIgnore
    private Integer id;
    private String bidderName;
    private LocalDateTime bidDate;
    @JsonIgnore
    private Integer lotId;
    public static BidDTO bidToDTO(Bid bid){
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setBidderName(bid.getBidderName());
        bidDTO.setBidDate(bid.getBidDate());
        bidDTO.setLotId(bid.getLot().getId());
        return bidDTO;
    }
    public Bid toBid(){
       Bid bid = new Bid();
       bid.setId(this.getId());
       bid.setBidderName(this.getBidderName());
       bid.setBidDate(this.getBidDate());
       return bid;
    }
}
