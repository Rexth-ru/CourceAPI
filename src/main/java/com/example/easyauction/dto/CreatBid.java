package com.example.easyauction.dto;

import com.example.easyauction.model.Bid;
import lombok.Data;

@Data
public class CreatBid {
    private String bidderName;
    public Bid toBid() {
        Bid bid = new Bid();
        bid.setBidderName(this.getBidderName());
        return bid;
    }
    public static CreatBid bidToCreatBid(Bid bid) {
       CreatBid creatBid = new CreatBid();
       creatBid.setBidderName(bid.getBidderName());
       return creatBid;
}
}
