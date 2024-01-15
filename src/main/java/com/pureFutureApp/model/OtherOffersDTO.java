package com.pureFutureApp.model;

import com.pureFutureApp.model.entity.Offer;

public class OtherOffersDTO extends MyOfferDTO {

    private String sellerUsername;

    public OtherOffersDTO() {
    }

    public OtherOffersDTO(Offer offer) {
        super(offer);
        sellerUsername = offer.getCreatedBy().getUsername();
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }
}
