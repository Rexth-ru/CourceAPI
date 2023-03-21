package com.example.easyauction.dto;

import com.example.easyauction.model.Bid;
import com.example.easyauction.model.Lot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BidDTO {
    @JsonIgnore
    private Integer id;
    private String bidderName;
    private String bidDate;
    @JsonIgnore
    private Integer lotId;
    public static BidDTO bidToDTO(Bid bid){
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setBidderName(bid.getBidderName());
        bidDTO.setBidDate(bid.getBidDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        bidDTO.setLotId(bid.getLot().getId());
        return bidDTO;
    }
    public Bid toBid(){
       Bid bid = new Bid();
       bid.setId(this.getId());
       bid.setBidderName(this.getBidderName());
//       bid.setBidDate(LocalDateTime.parse(this.getBidDate(), DateTimeFormatter.ISO_DATE_TIME));

       return bid;
    }
}
