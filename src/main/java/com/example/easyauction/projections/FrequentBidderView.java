package com.example.easyauction.projections;

import java.time.LocalDateTime;

public interface FrequentBidderView {
    LocalDateTime getBidDate();
    String getBidderName();
}
