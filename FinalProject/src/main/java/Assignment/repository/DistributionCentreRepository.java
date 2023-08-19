package Assignment.repository;

import Assignment.model.DistributionCentre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistributionCentreRepository {
    @Autowired
    DistributionCentreRepository distributionCentreRepository = null;

    public default DistributionCentre findClosestCentre() {
        List<DistributionCentre> centres = distributionCentreRepository.findAll();
        DistributionCentre closest = null;
        double minDistance = Double.MAX_VALUE;

        for (DistributionCentre centre : centres) {
            double distance = calculateDistance(centre.getLatitude(), centre.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                closest = centre;
            }
        }
        return closest;
    }

    double calculateDistance(double latitude, double longitude);

    List<DistributionCentre> findAll();
}
