package Assignment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import Assignment.model.Item;

public interface ItemRepositoryPaginated extends PagingAndSortingRepository<Item, Long>{
}
