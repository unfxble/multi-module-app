package ru.alexbat.catalogue.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewProductPayload(

        @NotBlank(message = "{catalogue.products.create.errors.title.not-empty}")
        @Size(min = 3, max = 50, message ="{catalogue.products.create.errors.title.invalid-length}")
        String title,

        @Size(max = 1000, message ="{catalogue.products.create.errors.details.invalid-length}")
        String details) {
}
