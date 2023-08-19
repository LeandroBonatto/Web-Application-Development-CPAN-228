package Assignment.model.dto;

import Assignment.model.Item.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemsSearch {
    private int createYear;
    private Brand brandFrom;
}
