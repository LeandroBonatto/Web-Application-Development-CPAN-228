package com.cpan252.tekkenreborn.repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cpan252.tekkenreborn.model.Item;
import com.cpan252.tekkenreborn.model.Item.Brand;

//It will be an interface that defines operations with the fighter
//table in the database
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBrandAndYear(String brand, LocalDate startDate, LocalDate endDate);
}
