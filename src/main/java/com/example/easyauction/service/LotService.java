package com.example.easyauction.service;

import com.example.easyauction.dto.BidDTO;
import com.example.easyauction.dto.CreatLot;
import com.example.easyauction.dto.FullLot;
import com.example.easyauction.dto.LotDTO;
import com.example.easyauction.en.Status;
import com.example.easyauction.model.Bid;
import com.example.easyauction.model.Lot;
import com.example.easyauction.projections.FrequentBidderView;
import com.example.easyauction.repository.LotRepository;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.util.Timer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Lot lot = lotRepository.findById(id).orElse(null);
        LotDTO lotDTO = LotDTO.lotToDTO(lot);
        FullLot fullLot = new FullLot();
        fullLot.setLotDTO(lotDTO);
        fullLot.setCurrentPrice(currentPrice(id,
                lotDTO.getStartPrice(), lotDTO.getBidPrice()));
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
        LotDTO lotDTO = LotDTO.lotToDTO(lotRepository.findById(id).get());
        if (lotDTO == null){
            throw new IOException("лот не найден");
        }
        lotDTO.setStatus(String.valueOf(Status.STARTED));
    }
    public void stopBidding(Integer id) throws IOException {
        LotDTO lotDTO = LotDTO.lotToDTO(lotRepository.findById(id).get());
        if (lotDTO == null){
            throw new IOException("лот не найден");
        }
        lotDTO.setStatus(String.valueOf(Status.STOPPED));
    }
    public Collection<LotDTO> findAllLotsByStatus(String status, Integer page){
        PageRequest pageRequest = PageRequest.of(page,10);
        Collection<Lot> lots = lotRepository.findLotsByStatus(status, pageRequest);
        return lots.stream().map(LotDTO::lotToDTO).collect(Collectors.toList());
    }
    public FrequentBidderView findMostFrequentBidder(Integer id){
       return lotRepository.findBidMaxLot(id);
    }

    private boolean checkField(Lot lot){
        if ((lot.getTitle().length()>64 && lot.getTitle().length()<3)||
                (lot.getStatus() == null) ||
                (lot.getDescription().length()>4096 && lot.getDescription().length()<1)||
                (lot.getStartPrice()<1)||(lot.getBidPrice()<1)){
          return false;
        }
        return true;
    }
    private Integer currentPrice(Integer idLot, Integer startPrice, Integer bidPrice){
       return lotRepository.countBidByIdLot(idLot)*bidPrice+startPrice;
    }
    public void writeLotsToCsv(List<LotDTO> lots, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (LotDTO lot : lots) {
                printer.printRecord(lot.getId(), lot.getTitle(), lot.getStatus(), lotRepository.findBidMaxLot(lot
                        .getId()), currentPrice(lot.getId(), lot.getStartPrice(), lot.getBidPrice()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Collection<LotDTO> findAll(){
       return lotRepository.findAll().stream().map(LotDTO::lotToDTO).collect(Collectors.toList());
    }
}
