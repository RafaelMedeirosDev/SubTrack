package com.example.SubTrack.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;
import com.example.SubTrack.shared.dtos.TokenDataDTO;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionsController {
  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @GetMapping("")
  public List<Subscription> get(@RequestAttribute("tokenData") TokenDataDTO tokenData) {
    return this.subscriptionRepository.getSubscriptionsByUserId(UUID.fromString(tokenData.id()));
  }
}
