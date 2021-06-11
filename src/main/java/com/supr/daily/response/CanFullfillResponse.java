package com.supr.daily.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CanFullfillResponse {

    @JsonProperty("can_fulfil")
    Boolean canFulfill;
}
