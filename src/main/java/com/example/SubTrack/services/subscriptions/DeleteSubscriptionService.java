package com.example.SubTrack.services.subscriptions;

import java.util.UUID;
import com.example.SubTrack.entities.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SubTrack.repositories.SubscriptionRepository;

@Service
public class DeleteSubscriptionService {

    @Autowired
    private FindOneSubscriptionService findOneSubscriptionService;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public void execute(UUID id, UUID userId) {
        Subscription subscription = findOneSubscriptionService.execute(id, userId);
        subscriptionRepository.delete(subscription);
    }
}
