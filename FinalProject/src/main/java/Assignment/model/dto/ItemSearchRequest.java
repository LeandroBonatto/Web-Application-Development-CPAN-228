
package Assignment.model.dto;

import Assignment.model.Item.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemSearchRequest {
    private String name;
    private String brand;

}
