package com.example.easyauction.repository;

import com.example.easyauction.en.Status;
import com.example.easyauction.model.Bid;
import com.example.easyauction.model.Lot;
import com.example.easyauction.projections.FrequentBidderView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface LotRepository extends JpaRepository<Lot, Integer> {
    Collection<Lot> findLotsByStatus(String status, PageRequest pageRequest);
    @Query(value = "select count (s) from bid s where lot_id=?1",nativeQuery = true)
    Integer countBidByIdLot(Integer id);
//    @Query(value = "select count (*) from bid full join lot l on l.id = bid.lot_Id " +
//            "where l.id=?1",nativeQuery = true)
//    Integer countBidByIdLot(Integer id);
@Query(value = "select * from bid where lot_id=?1 order by bid_date desc limit 1",nativeQuery = true)
Bid findLastBidByIdLot(Integer id);
//    @Query(value = "select * from bid b full outer join lot l on l.id = b.lot_Id " +
//            "where l.id=?1 order by b.bid_date desc limit 1",nativeQuery = true)
//    Bid findLastBidByIdLot(Integer id);
//    @Query(value = "select bidder_name as bidderName, max (bid_date) as bidDate " +
//            "from bid b full outer join lot l on l.id = b.lot_Id where lot_id = ?1 " +
//            "group by bidder_name order by count(*) desc limit 1",nativeQuery = true)
//    FrequentBidderView findBidMaxLot(Integer id);


    @Query(value = "select bidder_name as bidderName, max (bid_date) as bidDate " +
            "from bid where lot_id = ?1 group by bidder_name order by count(*) desc limit 1",nativeQuery = true)
    FrequentBidderView findBidMaxLot(Integer id);
}