package com.example.SubTrack.services.subscriptions;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;
import com.example.SubTrack.shared.dtos.CreateSubscriptionDto;

@Service
public class CreateSubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription execute(CreateSubscriptionDto data, UUID userId){

        int day = adjustDay(data.billingDay());
        Subscription subscription = new Subscription(data.platformName(), data.value(), day, userId);
        return this.subscriptionRepository.save(subscription);
    }

    private int adjustDay (int day){
        return Math.min(day, 31);
    }
}
