package cpan228.finalprojectdistributioncenter.model.dto;

import cpan228.finalprojectdistributioncenter.model.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class itemSearch {
    private String name;
    private Item.Brand brandFrom;
}
