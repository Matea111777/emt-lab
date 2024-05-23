package com.example.sheltermanagment.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;

public class AdoptionItemId extends DomainObjectId {
    private AdoptionItemId() {
        super(AdoptionItemId.randomId(ShelterID.class).getId());
    }

    public AdoptionItemId(String uuid) {
        super(uuid);
    }
}
