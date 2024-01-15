package com.pureFutureApp.service.impl;

import com.pureFutureApp.model.*;
import com.pureFutureApp.model.entity.Condition;
import com.pureFutureApp.model.entity.Offer;
import com.pureFutureApp.model.entity.User;
import com.pureFutureApp.repository.ConditionRepository;
import com.pureFutureApp.repository.OfferRepository;
import com.pureFutureApp.repository.UserRepository;
import com.pureFutureApp.service.LoggedUser;
import com.pureFutureApp.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public OfferServiceImpl(OfferRepository offerRepository, ConditionRepository conditionRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public OfferHomeDTO getOffersFotHomePage() {
        List<Offer> offers = offerRepository.findAll();

        List<MyOfferDTO> myOffers = new ArrayList<>();
        List<BoughtOffersDTO> boughtOffers = new ArrayList<>();
        List<OtherOffersDTO> otherOffers = new ArrayList<>();

        for (Offer offer : offers) {
            String loggedUsername = loggedUser.getUsername();

            if (offer.getBoughtBy() == null && offer.getCreatedBy().getUsername().equals(loggedUsername)) {
                myOffers.add(new MyOfferDTO(offer));
            } else if (offer.getBoughtBy() != null && offer.getBoughtBy().getUsername().equals(loggedUsername)) {
                boughtOffers.add(new BoughtOffersDTO(offer));
            } else if (offer.getBoughtBy() == null) {
                otherOffers.add(new OtherOffersDTO(offer));
            }
        }

        return new OfferHomeDTO(myOffers, boughtOffers, otherOffers);
    }

    @Override
    public boolean create(OfferCreateBindingModel offerCreateBindingModel) {
        Condition condition = conditionRepository.findByName(offerCreateBindingModel.getCondition());
        User user = userRepository.findByUsername(loggedUser.getUsername());

        if (condition != null && user != null) {
            Offer offer = new Offer(offerCreateBindingModel, condition, user);

            offerRepository.save(offer);
            return true;
        }

        return false;
    }

    @Override
    public void buy(UUID id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);

        if (optionalOffer.isPresent()) {
            User user = userRepository.findByUsername(loggedUser.getUsername());
            Offer offer = optionalOffer.get();

            offer.setBoughtBy(user);

            offerRepository.save(offer);
        }
    }
}
