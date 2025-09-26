package com.example.SubTrack.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SubTrack.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
  Page<Subscription> getSubscriptionsByUserId(UUID userId, Pageable pageable);
  
  @Query("SELECT s FROM Subscription s WHERE s.id = :id AND s.userId = :userId")
  Optional<Subscription> findSubscriptionByIdAndUserId(UUID id, UUID userId);
}
