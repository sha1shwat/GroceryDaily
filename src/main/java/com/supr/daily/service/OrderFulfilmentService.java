package com.supr.daily.service;

import com.supr.daily.exception.OrderReservationException;
import com.supr.daily.model.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderFulfilmentService {

    Boolean canFulfilOrder(OrderRequest orderRequest);

    void reserveOrder(OrderRequest orderRequest) throws OrderReservationException;
}
