package com.example.sheltermanagment.domain.value_objects;

import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sheltermanagment.domain.models.ShelterID;
import jakarta.persistence.Embeddable;

@Embeddable
public class PetId extends DomainObjectId {
    public PetId() {
        super(PetId.randomId(PetId.class).getId());
    }

    public PetId(String uuid) {
        super(uuid);
    }



}
