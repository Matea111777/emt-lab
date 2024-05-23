package com.example.sheltermanagment.service;

import com.example.sheltermanagment.domain.exceptions.AdoptionItemIdNotExistException;
import com.example.sheltermanagment.domain.exceptions.ShelterIdNotExistException;
import com.example.sheltermanagment.domain.models.AdoptionItemId;
import com.example.sheltermanagment.domain.models.Shelter;
import com.example.sheltermanagment.domain.models.ShelterID;
import com.example.sheltermanagment.service.forms.AdoptionItemForm;
import com.example.sheltermanagment.service.forms.ShelterForm;

import java.util.List;
import java.util.Optional;

public interface ShelterService {

    ShelterID placeAdoption(ShelterForm shelterForm);

    List<Shelter> findAll();

    Optional<Shelter> findById(ShelterID id);

    void addItem(ShelterID shelterId, AdoptionItemForm adoptionItemForm) throws ShelterIdNotExistException;

    void deleteItem(ShelterID shelterIDId, AdoptionItemId adoptionItemid) throws ShelterIdNotExistException, AdoptionItemIdNotExistException;






}
