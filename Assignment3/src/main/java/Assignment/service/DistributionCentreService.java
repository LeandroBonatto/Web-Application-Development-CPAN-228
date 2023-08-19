package Assignment.service;

import org.springframework.stereotype.Service;

@Service
public class DistributionCentreService {

    // warehouse coordinates
    private static final double WAREHOUSE_LATITUDE = 43.70;
    private static final double WAREHOUSE_LONGITUDE = -79.42;

    public double calculateDistance(double centreLat, double centreLon) {
        final int R = 6371; // Radius of the earth in kilometers

        double latDistance = Math.toRadians(centreLat - WAREHOUSE_LATITUDE);
        double lonDistance = Math.toRadians(centreLon - WAREHOUSE_LONGITUDE);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(WAREHOUSE_LATITUDE)) * Math.cos(Math.toRadians(centreLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // returns the distance in kilometers
    }
}
