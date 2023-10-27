package org.example.receiptprocessor.models;

public class PointsDto {
    int points;

    // Constructors
    public PointsDto(int points) {
        this.points = points;
    }

    public PointsDto() {
    };

    // Getter
    public int getPoints() {
        return points;
    }

    // Setter
    public void setPoints(int points) {
        this.points = points;
    }
}
