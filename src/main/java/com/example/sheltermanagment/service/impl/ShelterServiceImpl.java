package com.example.sheltermanagment.service.impl;

import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.infra.DomainEventPublisher;
import com.example.sheltermanagment.domain.exceptions.AdoptionItemIdNotExistException;
import com.example.sheltermanagment.domain.exceptions.ShelterIdNotExistException;
import com.example.sheltermanagment.domain.models.AdoptionItemId;
import com.example.sheltermanagment.domain.models.Shelter;
import com.example.sheltermanagment.domain.models.ShelterID;
import com.example.sheltermanagment.domain.repository.ShelterRepository;
import com.example.sheltermanagment.service.ShelterService;
import com.example.sheltermanagment.service.forms.AdoptionItemForm;
import com.example.sheltermanagment.service.forms.ShelterForm;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.xml.sax.SAXException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;
    @Autowired
    private LocalValidatorFactoryBean validator;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public ShelterID placeAdoption(ShelterForm shelterForm) {
        Objects.requireNonNull(shelterForm,"ShelterForm must be not null");
        BindingResult bindingResult = new BeanPropertyBindingResult(shelterForm, "shelterForm");
        validator.validate(shelterForm, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation failed for ShelterForm");
        }

        var newAdoption = shelterRepository.saveAndFlush(toDomainObject(shelterForm));
       // newAdoption.getAdoptionItemsList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getPetId().getId(),item.getQuantity())));
        return newAdoption.getId();
    }
    private Shelter toDomainObject(ShelterForm orderForm) {
        var order = new Shelter(Instant.now());
        orderForm.getItems().forEach(item->order.addItem(item.getPet(),item.getQuantity()));
        return order;
    }

    @Override
    public List<Shelter> findAll() {
        return shelterRepository.findAll();
    }

    @Override
    public Optional<Shelter> findById(ShelterID id) {
        return shelterRepository.findById(id);
    }

    @Override
    public void addItem(ShelterID shelterId, AdoptionItemForm adoptionItemForm) throws ShelterIdNotExistException {
        Shelter order = shelterRepository.findById(shelterId).orElseThrow(ShelterIdNotExistException::new);
        order.addItem(adoptionItemForm.getPet(),adoptionItemForm.getQuantity());
        shelterRepository.saveAndFlush(order);
       // domainEventPublisher.publish(new OrderItemCreated(adoptionItemForm.getPet().getPetId().getId(),adoptionItemForm.getQuantity()));

    }

    @Override
    public void deleteItem(ShelterID shelterIDId, AdoptionItemId adoptionItemid) throws ShelterIdNotExistException, AdoptionItemIdNotExistException {
        Shelter order = shelterRepository.findById(shelterIDId).orElseThrow(ShelterIdNotExistException::new);
        order.removeItem(adoptionItemid);
        shelterRepository.saveAndFlush(order);

    }
}
