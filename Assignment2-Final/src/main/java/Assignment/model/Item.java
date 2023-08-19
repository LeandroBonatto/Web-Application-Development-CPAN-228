package Assignment.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Min(2022)
    private Integer createYear;
    @NotNull
    @DecimalMin(value = "1000", inclusive = false)
    private BigDecimal price;
    public enum Brand {
        BALENCIAGA("Balenciaga"), STONEISLAND("Stone Island"), DIOR("Dior"), GUCCI("Gucci"), LOUISVUITTON("Louis Vuitton"), OFFWHITE("Off-White"), SUPREME("Supreme"), VERSACE("Versace");
        private String title;
        private Brand(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }
    }
    private Brand brandFrom;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
