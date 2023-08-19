package cpan228.finalprojectdistributioncenter.model.dto;

import cpan228.finalprojectdistributioncenter.model.DistributionCenter;
import cpan228.finalprojectdistributioncenter.model.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateCenter {
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "distributionCenter", cascade = CascadeType.ALL)
    private List<Item> items;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    public DistributionCenter toDistributionCenter() {
        return DistributionCenter.builder()
                .name(this.getName())
                .items(this.getItems())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .build();
    }
}
