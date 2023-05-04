package com.example.easyauction.repository;
import com.example.easyauction.en.Status;
import com.example.easyauction.model.Lot;
import com.example.easyauction.prijection.BidNameDate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {
    Collection<Lot> findLotsByStatus(Status status, PageRequest pageRequest);
    @Query(value = "select count (s) from bid s where s.lot_id= ?1 ",nativeQuery = true)
    Integer countBidByIdLot(Integer id);
    @Query(value = "select bidder_name as bidderName, max(bid_date) as bidDate from bid where lot_id= ?1 group by bidder_name ",nativeQuery = true)
    BidNameDate findLastBidByIdLot(Integer id);

    @Query(value = "select bidder_name as bidderName, max (bid_date) as bidDate from bid " +
            "where lot_id= ?1 group by bidder_name order by count(*) desc limit 1",nativeQuery = true)
    BidNameDate findBidMaxLot(Integer id);
}
