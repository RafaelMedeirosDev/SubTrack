package com.example.SubTrack.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;
import com.example.SubTrack.services.subscriptions.CreateSubscriptionService;
import com.example.SubTrack.services.subscriptions.DeleteSubscriptionService;
import com.example.SubTrack.services.subscriptions.FindOneSubscriptionService;
import com.example.SubTrack.services.subscriptions.ListSubscriptionService;
import com.example.SubTrack.services.subscriptions.UpdateSubscriptionService;
import com.example.SubTrack.shared.dtos.CreateSubscriptionDto;
import com.example.SubTrack.shared.dtos.TokenDataDTO;
import com.example.SubTrack.shared.dtos.UpdateSubscriptionDto;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionsController {
  @Autowired
  private SubscriptionRepository subscriptionRepository;
  @Autowired
  private FindOneSubscriptionService findOneSubscriptionService;
  @Autowired
  private CreateSubscriptionService createSubscriptionService;
  @Autowired
  private UpdateSubscriptionService updateSubscriptionService;
  @Autowired
  private DeleteSubscriptionService deleteSubscriptionService;
  @Autowired
  private ListSubscriptionService listSubscriptionService;
  

  @GetMapping("")
  public ResponseEntity<Page<Subscription>> list(@RequestAttribute("tokenData") TokenDataDTO tokenData, Pageable pageable) {
    Page<Subscription> subscriptions = listSubscriptionService.execute(UUID.fromString(tokenData.id()), pageable);
    return ResponseEntity.ok(subscriptions);
  }

  @GetMapping("/{id}")
  public Subscription get(@PathVariable("id") UUID id, @RequestAttribute("tokenData") TokenDataDTO tokenData) {
    return this.findOneSubscriptionService.execute(id, UUID.fromString(tokenData.id()));
  }

  @PostMapping()
  public Subscription register(@RequestBody CreateSubscriptionDto body, @RequestAttribute("tokenData") TokenDataDTO tokenData) {
    return this.createSubscriptionService.execute(body, UUID.fromString(tokenData.id()));
  }

  @PatchMapping("/{id}")
  public Subscription update(@PathVariable("id") UUID id,@RequestBody UpdateSubscriptionDto body, @RequestAttribute("tokenData") TokenDataDTO tokenData){
    return this.updateSubscriptionService.execute(id, body, UUID.fromString(tokenData.id()));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") UUID id, @RequestAttribute("tokenData") TokenDataDTO tokenData){
    this.deleteSubscriptionService.execute(id, UUID.fromString(tokenData.id()));
  }
}
