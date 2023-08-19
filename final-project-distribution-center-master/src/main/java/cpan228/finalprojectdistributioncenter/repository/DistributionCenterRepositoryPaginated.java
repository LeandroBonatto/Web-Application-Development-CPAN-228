package cpan228.finalprojectdistributioncenter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cpan228.finalprojectdistributioncenter.model.DistributionCenter;

@Repository
public interface DistributionCenterRepositoryPaginated extends PagingAndSortingRepository<DistributionCenter, Long>{
}
