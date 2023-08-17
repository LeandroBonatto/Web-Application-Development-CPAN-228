package com.cpan252.clotheswarehouse.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table
public class Item {
    public enum fashionableBrands {
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior");

        private String title;

        private fashionableBrands(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    @Id
    private int id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Brand is required")
    private String brand;
    @Min(value = 2021, message = "Year of creation must be greater than 2021")
    private int yearOfCreation;
    @Min(value = 1000, message = "Price must be greater than 1000")
    private double price;
    private fashionableBrands fashionableBrands;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
