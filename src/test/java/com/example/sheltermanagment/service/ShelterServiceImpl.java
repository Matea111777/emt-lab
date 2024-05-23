package com.example.sheltermanagment.service;


import com.example.sharedkernel.domain.credinentials.Category;
import com.example.sheltermanagment.domain.exceptions.ShelterIdNotExistException;
import com.example.sheltermanagment.domain.models.Shelter;
import com.example.sheltermanagment.domain.models.ShelterID;
import com.example.sheltermanagment.domain.value_objects.Pet;
import com.example.sheltermanagment.domain.value_objects.PetId;
import com.example.sheltermanagment.service.forms.AdoptionItemForm;
import com.example.sheltermanagment.service.forms.ShelterForm;
import com.example.sheltermanagment.xport.PetClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class ShelterServiceImpl {

    @Autowired
    private ShelterService orderService;

    @Autowired
    private PetClient productClient;



    private static Pet newProduct(Category cat) {
        Pet p = new Pet(PetId.randomId(PetId.class),cat, 0);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        AdoptionItemForm oi1 = new AdoptionItemForm();
        oi1.setPet(newProduct(Category.DOG));
        oi1.setQuantity(1);

        AdoptionItemForm oi2 = new AdoptionItemForm();
        oi2.setPet(newProduct(Category.CAT));
        oi2.setQuantity(2);

        ShelterForm orderForm = new ShelterForm();
        orderForm.setItems(Arrays.asList(oi1,oi2));

        ShelterID newOrderId = orderService.placeAdoption(orderForm);
        Shelter newOrder = orderService.findById(newOrderId).orElseThrow(ShelterIdNotExistException::new);
        Assertions.assertEquals(newOrder.getAdoptionItemsList().size(),2);

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Pet> productList = productClient.findAll();
        Pet p1 = productList.get(0);
        Pet p2 = productList.get(1);

        AdoptionItemForm oi1 = new AdoptionItemForm();
        oi1.setPet(p1);
        oi1.setQuantity(1);

        AdoptionItemForm oi2 = new AdoptionItemForm();
        oi2.setPet(p2);
        oi2.setQuantity(2);

        ShelterForm orderForm = new ShelterForm();
        orderForm.setItems(Arrays.asList(oi1,oi2));

        ShelterID newOrderId = orderService.placeAdoption(orderForm);
        Shelter newOrder = orderService.findById(newOrderId).orElseThrow(ShelterIdNotExistException::new);


        Assertions.assertEquals(newOrder.getAdoptionItemsList().size(),2);
    }


}
