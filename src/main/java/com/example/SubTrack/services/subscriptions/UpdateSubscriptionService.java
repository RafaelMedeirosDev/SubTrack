package com.example.SubTrack.services.subscriptions;

import java.time.LocalDate;
import java.time.YearMonth;
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

        if(data.billingDate() != null) {
            subscription.setBillingDate(adjustDate(data.billingDate()));
        }
        

        return subscriptionRepository.save(subscription);
    }

    private LocalDate adjustDate(LocalDate billingDate) {
        YearMonth yearMonth = YearMonth.of(billingDate.getYear(), billingDate.getMonth());
        int lastDay = yearMonth.lengthOfMonth();
        int day = Math.min(billingDate.getDayOfMonth(), lastDay);
        return LocalDate.of(billingDate.getYear(), billingDate.getMonth(), day);
    }
}
