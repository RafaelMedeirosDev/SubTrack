package com.example.SubTrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "platform_name")
    private String platformName;
    @Column(name = "user_id")
    private UUID userId;
    @Column
    private BigDecimal value;
    @Column(name = "billing_date")
    private Date billingDate;

    public Subscription(String platformName, BigDecimal value, Date billingDate, UUID userId) {
        this.platformName = platformName;
        this.value = value;
        this.billingDate = billingDate;
        this.userId = userId;
    }
}
