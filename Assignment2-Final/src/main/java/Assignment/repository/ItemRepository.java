package Assignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import Assignment.model.Item;
import Assignment.model.Item.Brand;

public interface ItemRepository extends CrudRepository<Item, Long>{
    List<Item> findByCreateYearAndBrandFromEquals(int createYear, Brand brandFrom);
}
