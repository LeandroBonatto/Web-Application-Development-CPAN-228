package Assignment.model;

import jakarta.persistence.*;
import java.util.List;

public class DistributionCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "distributionCentre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    private double latitude;
    private double longitude;

}

