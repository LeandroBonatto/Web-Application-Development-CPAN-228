package Assignment.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DistributionCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double latitude;
    private double longitude;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "centre_id")
    private List<Item> itemsAvailable = new ArrayList<>();

}

