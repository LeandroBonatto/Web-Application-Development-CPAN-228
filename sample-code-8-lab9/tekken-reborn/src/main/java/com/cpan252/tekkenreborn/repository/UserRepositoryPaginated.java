package com.cpan252.tekkenreborn.repository;

import com.cpan252.tekkenreborn.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryPaginated extends PagingAndSortingRepository<User, Long> {
}


