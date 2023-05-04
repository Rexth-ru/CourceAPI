package com.example.easyauction.service;

import com.example.easyauction.dto.*;
import com.example.easyauction.en.Status;
import com.example.easyauction.model.Lot;
import com.example.easyauction.prijection.BidNameDate;
import com.example.easyauction.repository.LotRepository;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Data
public class LotService {
    private final LotRepository lotRepository;

    public FullLot getFullLotById(Integer id){
        Lot lot = lotRepository.findById(id).get();
        if (lot==null){
            return null;
        }
        FullLot fullLot = FullLot.lotToFullLot(lot);
        fullLot.setCurrentPrice(currentPrice(id, fullLot.getStartPrice(), fullLot.getBidPrice()));
        fullLot.setLastBid(lotRepository.findLastBidByIdLot(id));
        return fullLot;
    }
    public LotDTO createLot(CreatLot creatLot) {
        Lot lot = creatLot.toLot();
        lot.setStatus(Status.CREATED);
        lotRepository.save(lot);
        return LotDTO.lotToDTO(lot);
    }
    public void startBidding(Integer id) throws IOException {
        Lot lot = lotRepository.findById(id).orElse(null);
        if (lot==null){
            throw new IOException("лот не найден");
        }
        lot.setStatus(Status.STARTED);
    }
    public void stopBidding(Integer id) throws IOException {
        Lot lot = lotRepository.findById(id).orElse(null);
        if (lot==null){
            throw new IOException("лот не найден");
        }
        lot.setStatus(Status.STOPPED);
    }
    public Collection<LotDTO> findAllLotsByStatus(Status status, Integer page){
        PageRequest pageRequest = PageRequest.of(page,10);

        Collection<Lot> lots = lotRepository.findLotsByStatus(status, pageRequest);
        return lots.stream().map(LotDTO::lotToDTO).collect(Collectors.toList());
    }
    public BidNameDate findMostFrequentBidder(Integer id){
      return lotRepository.findBidMaxLot(id);
    }
    private Integer currentPrice(Integer idLot, Integer startPrice, Integer bidPrice){
       return lotRepository.countBidByIdLot(idLot)*bidPrice+startPrice;
    }
    public void writeLotsToCsv(List<FullLot> lots, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (FullLot lot : lots) {
                printer.printRecord(lot.getId(), lot.getTitle(), lot.getStatus(),
                        lotRepository.findLastBidByIdLot(lot.getId()).getBidderName(),
                        currentPrice(lot.getId(), lot.getStartPrice(), lot.getBidPrice()),
                        lot.getStartPrice() , lot.getBidPrice());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<FullLot> findAll(){
       return lotRepository.findAll().stream().map(FullLot::lotToFullLot).collect(Collectors.toList());
    }
}
