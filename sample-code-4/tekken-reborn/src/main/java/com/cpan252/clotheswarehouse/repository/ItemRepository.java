package com.cpan252.clotheswarehouse.repository;

import org.springframework.data.repository.CrudRepository;

import com.cpan252.clotheswarehouse.model.Item;

//It will be an interface that defines operations with the fighter
//table in the database
public interface ItemRepository extends CrudRepository<Item, Long> {
}
