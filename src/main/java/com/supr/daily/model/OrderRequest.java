package com.supr.daily.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @JsonProperty("customer_id")
    String customerId; // Customer ID
    @JsonProperty("warehouse_id")
    String warehouseId; // ID of Warehouse which serves the Customer
    @JsonProperty("delivery_date")
    String deliveryDate; // Delivery Date
    List<ItemRequest> items; //
}
