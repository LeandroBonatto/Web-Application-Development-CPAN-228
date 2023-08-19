package cpan228.finalprojectdistributioncenter.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cpan228.finalprojectdistributioncenter.model.DistributionCenter;
import cpan228.finalprojectdistributioncenter.model.Item;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateItem {

    private String name;
    private Integer createYear;
    private int quantity;
    private BigDecimal price;
    private Item.Brand brandFrom;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distribution_center_id", nullable = false)
    private DistributionCenter distributionCenter;
}
