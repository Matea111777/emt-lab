package com.example.sheltermanagment.service.forms;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShelterForm {
    @Valid
    @NotEmpty
    private List<AdoptionItemForm> items = new ArrayList<>();


}
