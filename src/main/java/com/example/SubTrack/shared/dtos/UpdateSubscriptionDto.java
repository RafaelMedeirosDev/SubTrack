package com.example.SubTrack.shared.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateSubscriptionDto(
    BigDecimal value,
    LocalDate billingDate
) {

}
