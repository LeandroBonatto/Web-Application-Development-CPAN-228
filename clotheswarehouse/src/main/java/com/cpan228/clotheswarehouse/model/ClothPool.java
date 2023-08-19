package com.cpan228.clotheswarehouse.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ClothPool {
    private List<Cloth> clothes;

    public ClothPool() {
        this.clothes = new ArrayList<>();
    }

    public void add(Cloth cloth) {
        this.clothes.add(cloth);
    }
}
