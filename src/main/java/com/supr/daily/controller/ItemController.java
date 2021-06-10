package com.supr.daily.controller;

import com.supr.daily.constants.UsefulConstants;
import com.supr.daily.model.OrderRequest;
import com.supr.daily.response.CanFullfillResponse;
import com.supr.daily.response.DataInfo;
import com.supr.daily.response.ReserveOrderResponse;
import com.supr.daily.service.OrderFulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suprDaily")
public class ItemController {

    @Autowired
    OrderFulfilmentService orderFulfilmentService;

    @GetMapping("/canFulfillOrder")
    public ResponseEntity<CanFullfillResponse> findFulfillOrder(@RequestBody OrderRequest orderRequest){

        boolean isPossible = orderFulfilmentService.canFulfilOrder(orderRequest);

        CanFullfillResponse canFullfillResponse = new CanFullfillResponse();
        canFullfillResponse.setCanFulfill(isPossible);

        return ResponseEntity.ok().body(canFullfillResponse);

    }

    @PostMapping("/reserve")
    public ResponseEntity<ReserveOrderResponse> findReserveOrder(@RequestBody OrderRequest orderRequest){

        ReserveOrderResponse response = new ReserveOrderResponse();
        response.setCode(UsefulConstants.CODE_SUCCESS);

        try{
            orderFulfilmentService.reserveOrder(orderRequest);

            DataInfo dataInfo = new DataInfo();
            dataInfo.setReserved(true);
            dataInfo.setMessage(UsefulConstants.RESERVE_SUCCESS);
            return ResponseEntity.ok().body(response);


        }catch (Exception e){

            DataInfo dataInfo = new DataInfo();
            dataInfo.setReserved(false);
            dataInfo.setMessage(UsefulConstants.RESERVE_FAILED);
            return ResponseEntity.ok().body(response);

        }
    }




}
