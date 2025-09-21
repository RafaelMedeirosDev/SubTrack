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

        
        Subscription subscription = new Subscription(data.platformName(), data.value(), data.billingDay(), userId);
        return this.subscriptionRepository.save(subscription);
    }

}
