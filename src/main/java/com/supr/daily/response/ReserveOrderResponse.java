package com.supr.daily.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReserveOrderResponse {

    String code;
    List<DataInfo> dataList;
}
