package com.example.SubTrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "platform_name")
    private String platformName;
    @Column(name = "user_id")
    private String userId;
    @Column
    private String value;
    @Column(name = "billing_date")
    private Date billingDate;
}
