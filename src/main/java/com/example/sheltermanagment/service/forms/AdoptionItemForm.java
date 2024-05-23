package com.example.sheltermanagment.service.forms;


import com.example.sheltermanagment.domain.value_objects.Pet;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdoptionItemForm {
   @NotNull
   private Pet pet;
   @Min(1)
   private int quantity=1;
}
