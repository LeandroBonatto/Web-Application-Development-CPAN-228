package cpan228.finalprojectdistributioncenter.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import cpan228.finalprojectdistributioncenter.model.Item;
import cpan228.finalprojectdistributioncenter.model.Item.Brand;
public interface ItemRepository extends CrudRepository<Item, Long>{
    List<Item> findByBrandFromEquals(Brand brandFrom);

    List<Item> findByNameEquals(String name);

    List<Item> findByNameEqualsAndAndBrandFromEquals(String name, Brand brandFrom);
}
