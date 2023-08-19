package com.cpan228.clotheswarehouse.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cloth {
    private int id;
    @NotBlank (message = "Cloth must have a name")
    private String name;
    @Min (value = 2021, message = "Year of creation must be at least 2021")
    private int yearOfCreation;
    @Min (value = 1000, message = "Price must be minimum $1000")
    private double price;

    private Brand brandFrom;

    public enum Brand {
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior");

        private String title;

        private Brand(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}

