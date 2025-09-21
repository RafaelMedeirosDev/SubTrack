package com.example.SubTrack.services.subscriptions;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SubTrack.entities.Subscription;
import com.example.SubTrack.repositories.SubscriptionRepository;
import com.example.SubTrack.shared.dtos.CreateSubscriptionDto;

@Service
public class CreateSubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription execute(CreateSubscriptionDto data, UUID userId){

        LocalDate billingDate = adjustDate(data.billingDate());
        Subscription subscription = new Subscription(data.platformName(), data.value(), billingDate, userId);
        return this.subscriptionRepository.save(subscription);
    }

    private LocalDate adjustDate (LocalDate billingDate){
        YearMonth yearMonth = YearMonth.of(billingDate.getYear(), billingDate.getMonth());
        int lastDay = yearMonth.lengthOfMonth();
        int day = Math.min(billingDate.getDayOfMonth(), lastDay);
        return LocalDate.of(billingDate.getYear(), billingDate.getMonth(), day);
    }
}
