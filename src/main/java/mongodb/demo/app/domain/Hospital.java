package mongodb.demo.app.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Location location;

    protected Hospital() {
    }

    private Hospital(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Hospital(Long id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public static Hospital of(String name) {
        return new Hospital(null, name);
    }

    public static Hospital of(String name, double x, double y) {
        return new Hospital(null, name, new Location(x, y));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
