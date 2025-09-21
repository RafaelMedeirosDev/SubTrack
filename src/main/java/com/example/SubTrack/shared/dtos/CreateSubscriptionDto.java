package com.example.SubTrack.shared.dtos;

import java.math.BigDecimal;

public record CreateSubscriptionDto(
    String platformName,
    BigDecimal value,
    Integer billingDay
) {
}
