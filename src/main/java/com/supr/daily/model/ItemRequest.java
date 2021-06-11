package com.supr.daily.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {

    @JsonProperty("item_id")
    Integer itemId;
    @JsonProperty("item_name")
    String itemName;
    @JsonProperty("category")
    String category; // Category that the items belongs to.
    @JsonProperty("quantity")
    Integer quantity;
}
