package com.example.sheltermanagment.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;

public class ShelterID extends DomainObjectId {
    private ShelterID() {
        super(ShelterID.randomId(ShelterID.class).getId());
    }

    public ShelterID(String uuid) {
        super(uuid);
    }

}
