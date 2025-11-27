package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data 
public class Product {
    
    @Id
    @GeneratedValue
    private Integer pid;

    @NotBlank(message = "Name is Mandatory")
    @Size(min = 3 , max = 15 , message = "Name should have 3 to 15 character")
    private String name;

    @NotNull(message = "Price is Mandatory")
    private Double price;

    @NotNull(message = "Quantity is Mandatory")
    private Integer qty; 
}
