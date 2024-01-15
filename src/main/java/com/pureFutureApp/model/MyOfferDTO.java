package com.pureFutureApp.model;

import com.pureFutureApp.model.entity.Offer;
import com.pureFutureApp.model.enums.ConditionName;

import java.util.UUID;

public class MyOfferDTO extends BoughtOffersDTO {

    private UUID id;
    private ConditionName condition;

    public MyOfferDTO() {
    }

    public MyOfferDTO(Offer offer) {
        super(offer);
        id = offer.getId();
        condition = offer.getCondition().getName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public void setCondition(ConditionName condition) {
        this.condition = condition;
    }
}
