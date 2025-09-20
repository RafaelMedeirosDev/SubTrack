package com.example.SubTrack.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;
import com.example.SubTrack.services.subscriptions.CreateSubscriptionService;
import com.example.SubTrack.services.subscriptions.FindOneSubscriptionService;
import com.example.SubTrack.shared.dtos.CreateSubscriptionDto;
import com.example.SubTrack.shared.dtos.TokenDataDTO;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionsController {
  @Autowired
  private SubscriptionRepository subscriptionRepository;
  @Autowired
  private FindOneSubscriptionService findOneSubscriptionService;
  @Autowired
  private CreateSubscriptionService CreateSubscriptionService;
  
  @GetMapping("")
  public List<Subscription> get(@RequestAttribute("tokenData") TokenDataDTO tokenData) {
    return this.subscriptionRepository.getSubscriptionsByUserId(UUID.fromString(tokenData.id()));
  }

  @GetMapping("/{id}")
  public Subscription get(@PathVariable("id") UUID id, @RequestAttribute("tokenData") TokenDataDTO tokenData) {
    return this.findOneSubscriptionService.execute(id, UUID.fromString(tokenData.id()));
  }

  @PostMapping()
  public Subscription register(@RequestBody CreateSubscriptionDto body, @RequestAttribute("tokenData") TokenDataDTO tokenData) {
    return this.CreateSubscriptionService.execute(body, UUID.fromString(tokenData.id()));
  }
}
