package org.example.GameLogic;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

//@Embeddable
public class Row {

    @ElementCollection
    private List<String> cells = new ArrayList<>();

    public Row() {
        // Initialize each row with 3 empty cells
        for (int i = 0; i < 3; i++) {
            cells.add(" ");
        }
    }

    public List<String> getCells() {
        return cells;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }
}