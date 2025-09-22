package com.example.SubTrack.shared.dtos;

import java.math.BigDecimal;

public record UpdateSubscriptionDto(
    BigDecimal value,
    Integer billingDay
) {

}
