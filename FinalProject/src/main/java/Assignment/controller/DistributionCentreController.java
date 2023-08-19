package Assignment.controller;

import Assignment.model.DistributionCentre;
import Assignment.model.Item;
import Assignment.service.DistributionCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distributionCentre")
public class DistributionCentreController {

    @Autowired
    private DistributionCentreService distributionCentreService;

    @PostMapping("/item")
    public ResponseEntity addItem(@RequestBody Item item) {
        distributionCentreService.addItem(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        distributionCentreService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<DistributionCentre>> listAllDistributionCentres() {
        List<DistributionCentre> distributionCentres = (List<DistributionCentre>) distributionCentreService.listAllDistributionCentres();
        return ResponseEntity.ok(distributionCentres);
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/item")
    public ResponseEntity<Item> requestItemByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        Item item = distributionCentreService.findItemByBrandAndName(brand, name);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/request-item")
    public String requestItem(@RequestParam String name, @RequestParam String brand, Model model) {
        List<DistributionCentre> centresWithItem = distributionCentreService.findCentresWithItem(brand, name);

        if (centresWithItem.isEmpty()) {
            model.addAttribute("errorMessage", "Stock can't be replenished");
            return "errorPage";
        }

        DistributionCentre closestCentre = getClosestCentre(centresWithItem);

        // Logic to reduce the item stock in the closestCentre and increase in the warehouse.
        return "successPage";
    }

    private DistributionCentre getClosestCentre(List<DistributionCentre> centres) {
        double warehouseLatitude = 43.70; // GTA Area Coordinate
        double warehouseLongitude = -79.42;

        DistributionCentre closestCentre = null;
        double shortestDistance = Double.MAX_VALUE;

        for (DistributionCentre centre : centres) {
            double distance = haversineDistance(warehouseLatitude, warehouseLongitude, centre.getLatitude(), centre.getLongitude());
            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestCentre = centre;
            }
        }
        return closestCentre;
    }

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return 0;
    }
}
