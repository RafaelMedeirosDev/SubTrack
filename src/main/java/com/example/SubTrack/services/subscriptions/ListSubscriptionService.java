package com.example.SubTrack.services.subscriptions;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;

@Service
public class ListSubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    public Page<Subscription> execute(UUID userId, Pageable pageable) {
        return this.subscriptionRepository.getSubscriptionsByUserId(userId, pageable);
    }
}
