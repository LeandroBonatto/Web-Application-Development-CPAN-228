
package Assignment.controller;

import Assignment.model.DistributionCentre;
import Assignment.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<DistributionCentre>> listAllDistributionCentres() {
        List<DistributionCentre> distributionCentres = distributionCentreService.listAllDistributionCentres();
        return ResponseEntity.ok(distributionCentres);
    }

    @GetMapping("/item")
    public ResponseEntity<Item> requestItemByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        Item item = distributionCentreService.findItemByBrandAndName(brand, name);
        return ResponseEntity.ok(item);
    }
}
