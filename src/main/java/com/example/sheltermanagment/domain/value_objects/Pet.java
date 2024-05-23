package com.example.sheltermanagment.domain.value_objects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.credinentials.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class Pet implements ValueObject {
    private final PetId petId;
    private final Category cat;

    private final int adobted;

    private Pet(){
        this.petId=PetId.randomId(PetId.class);
        this.cat=Category.CAT;
        this.adobted=0;
    }
    @JsonCreator
    public  Pet(PetId id,
                Category cat,
                int adobted) {
        this.petId = id;
        this.cat= cat;
        this.adobted = adobted;
    }

}
