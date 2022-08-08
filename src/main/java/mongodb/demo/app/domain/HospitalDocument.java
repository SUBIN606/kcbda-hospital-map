package mongodb.demo.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;


/** 동물병원 */
@Entity
@Document
public class Hospital {

    @Id
    private String id;
    private String name;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    protected Hospital() {
    }

    public Hospital(String name, GeoJsonPoint coordinates) {
        this.name = name;
        this.location = coordinates;
    }

    private Hospital(String name, double x, double y) {
        this.name = name;
        this.location = new GeoJsonPoint(x, y);
    }

    public static Hospital of(String name, double x, double y) {
        return new Hospital(name, x, y);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

}
