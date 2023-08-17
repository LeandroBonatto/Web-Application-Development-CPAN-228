package com.cpan252.tekkenreborn.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fighter {
    @NotBlank(message = "Fighter must have a name")
    private String name;
    @Max(value = 100, message = "Damage must be max 100")
    private Integer damagePerHit;
    @Min(value = 1000, message = "Health must be min 1000")
    private int health;
    @DecimalMin(value = "0", message = "Resistance must be min 0")
    @DecimalMax(value = "10", message = "Resistance must be max 10")
    private BigDecimal resistance;
    private Anime animeFrom;


    public enum Anime {
        NARUTO("Naruto"), BLEACH("Bleach"), ONE_PIECE("One Piece"), TEKKEN("Tekken");

        private String title;

        private Anime(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
