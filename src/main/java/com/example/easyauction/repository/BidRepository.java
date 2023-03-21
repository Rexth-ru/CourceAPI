package com.example.easyauction.repository;

import com.example.easyauction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, String> {
}
