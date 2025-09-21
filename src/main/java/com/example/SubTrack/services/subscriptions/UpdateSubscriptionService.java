package com.example.SubTrack.services.subscriptions;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;
import com.example.SubTrack.shared.dtos.UpdateSubscriptionDto;

@Service
public class UpdateSubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private FindOneSubscriptionService findOneSubscriptionService;

    public Subscription execute(UUID id, UpdateSubscriptionDto data, UUID userId) {
        
        Subscription subscription = findOneSubscriptionService.execute(id, userId);

        if(data.value() != null) {
            subscription.setValue(data.value());
        }

        if(data.billingDay() != null) {
            subscription.setBillingDay(adjustDay(data.billingDay()));
        }
        

        return subscriptionRepository.save(subscription);
    }

    private int adjustDay(int billingDay) {
        return Math.min(billingDay, 31);
    }
}
