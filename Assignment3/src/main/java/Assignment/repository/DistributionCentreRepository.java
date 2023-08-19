package Assignment.repository;

import Assignment.model.DistributionCentre;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DistributionCentreRepository {
    @Autowired
    private DistributionCentreRepository distributionCentreRepository;

    public DistributionCentre findClosestCentre() {
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
}
