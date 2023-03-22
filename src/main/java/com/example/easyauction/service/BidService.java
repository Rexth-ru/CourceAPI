package com.example.easyauction.service;

import com.example.easyauction.dto.BidDTO;
import com.example.easyauction.dto.CreatBid;
import com.example.easyauction.en.Status;
import com.example.easyauction.model.Bid;
import com.example.easyauction.model.Lot;

import com.example.easyauction.repository.BidRepository;
import com.example.easyauction.repository.LotRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

@Service
@Transactional
@Data
public class BidService {
    private final BidRepository bidRepository;
    private final LotRepository lotRepository;

    public BidDTO getFirstBidByIdLot(Integer idLot) {
        Lot lot = lotRepository.findById(idLot).orElse(null);
        Collection<Bid> bids = Objects.requireNonNull(lot).getBid();
        return BidDTO.bidToDTO(bids.stream().min(Comparator.comparing(Bid::getBidDate)).get());
    }
    public void createBid(Integer idLot, CreatBid creatBid) throws IOException {
        Lot lot = lotRepository.findById(idLot).orElse(null);
        if (lot.getStatus().equals(Status.CREATED) || lot.getStatus().equals(Status.STOPPED)) {
            throw new IOException();
        }
        Bid bid = creatBid.toBid();
        bid.setLot(lot);
        bid.setBidDate(LocalDateTime.now());
        BidDTO.bidToDTO(bidRepository.save(bid));
    }
}
