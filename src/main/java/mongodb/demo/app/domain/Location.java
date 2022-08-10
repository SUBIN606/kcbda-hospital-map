package mongodb.demo.app.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {

    private double x;
    private double y;

    protected Location() {
    }

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
