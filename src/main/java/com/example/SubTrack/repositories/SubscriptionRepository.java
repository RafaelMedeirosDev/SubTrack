package com.example.SubTrack.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SubTrack.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
  List<Subscription> getSubscriptionsByUserId(UUID userId);
}
