package com.example.SubTrack.shared.dtos;

import java.math.BigDecimal;
import java.sql.Date;

public record CreateSubscriptionDto(
    String platformName,
    BigDecimal value,
    Date billingDate
) {
}
