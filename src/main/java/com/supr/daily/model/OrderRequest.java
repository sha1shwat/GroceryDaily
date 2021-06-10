package com.supr.daily.model;

import java.util.Date;
import java.util.List;

public class OrderRequest {

    String customerId; // Customer ID
    String warehouseId; // ID of Warehouse which serves the Customer
    Date deliveryDate; // Delivery Date
    List<ItemRequest> items; //
}
