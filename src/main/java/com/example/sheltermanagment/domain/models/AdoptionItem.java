package com.example.sheltermanagment.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sheltermanagment.domain.value_objects.PetId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Getter
@AttributeOverride(name = "id", column = @Column(name = "adoptionitem_id"))
public class AdoptionItem extends AbstractEntity<AdoptionItemId> {
    private Integer quantity;


    private PetId petId;

    public AdoptionItem(@NonNull PetId petId,@NonNull Integer quantity){
        super(AdoptionItemId.randomId(AdoptionItemId.class));
        this.petId=petId;
        this.quantity=quantity;
    }

    public AdoptionItem() {

    }
}
