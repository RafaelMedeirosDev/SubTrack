package com.example.SubTrack.services.subscriptions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;

@Service
public class FindOneSubscriptionService {
  @Autowired SubscriptionRepository subscriptionRepository;

  public Subscription execute(UUID subscriptionId, UUID userId) {

    Optional<Subscription> subscription = this.subscriptionRepository.findSubscriptionByIdAndUserId(subscriptionId, userId);
    
    if(subscription.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");
    }

    return subscription.get();
  }
}
