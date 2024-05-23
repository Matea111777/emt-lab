package com.example.sheltermanagment.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.credinentials.Email;
import com.example.sharedkernel.domain.credinentials.PhoneNumber;
import com.example.sheltermanagment.domain.value_objects.Pet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
public class Shelter extends AbstractEntity<ShelterID>{
    private Instant adoptedOn;
    private String naziv;
    private String lokacija;
    private Integer kapacitet;
    // private Email email;
    private PhoneNumber phoneNumber;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<AdoptionItem> adoptionItemsList=new HashSet<>();

    public Shelter(){
        super(ShelterID.randomId(ShelterID.class));
    }

    public Shelter(Instant now){
        super(ShelterID.randomId(ShelterID.class));
        this.adoptedOn=now;
    }


    public AdoptionItem addItem(@NonNull Pet pet, int qnt){
        Objects.requireNonNull(pet,"Pet must be not null");
        var item=new AdoptionItem(pet.getPetId(),qnt);
        adoptionItemsList.add(item);
        return item;
    }
    public void removeItem(@NonNull AdoptionItemId id){
        Objects.requireNonNull(id,"Id must be not null");
        adoptionItemsList.removeIf(v->v.getId().equals(id));
    }
}
