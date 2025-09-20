package com.example.SubTrack.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SubTrack.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
  List<Subscription> getSubscriptionsByUserId(UUID userId);
  
  @Query("SELECT s FROM Subscription s WHERE s.id = :id AND s.userId = :userId")
  Optional<Subscription> findSubscriptionByIdAndUserId(UUID id, UUID userId);
}
