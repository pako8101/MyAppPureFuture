package com.pureFutureApp.service;


import com.pureFutureApp.model.OfferCreateBindingModel;
import com.pureFutureApp.model.OfferHomeDTO;

import java.util.UUID;

public interface OfferService {

    OfferHomeDTO getOffersFotHomePage();

    boolean create(OfferCreateBindingModel offerCreateBindingModel);

    void buy(UUID id);
}
