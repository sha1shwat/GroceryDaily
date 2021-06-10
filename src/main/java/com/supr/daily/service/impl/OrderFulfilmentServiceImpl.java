package com.supr.daily.service.impl;

import com.supr.daily.exception.OrderReservationException;
import com.supr.daily.model.OrderRequest;
import com.supr.daily.service.OrderFulfilmentService;

public class OrderFulfilmentServiceImpl implements OrderFulfilmentService {


    @Override
    public Boolean canFulfilOrder(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public void reserveOrder(OrderRequest orderRequest) throws OrderReservationException {

    }
}
