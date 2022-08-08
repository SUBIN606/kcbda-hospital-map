package mongodb.demo.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;


/** 동물병원 */
@Document
public class HospitalDocument {

    @Id
    private String id;
    private String name;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    protected HospitalDocument() {
    }

    public HospitalDocument(String name, GeoJsonPoint coordinates) {
        this.name = name;
        this.location = coordinates;
    }

    private HospitalDocument(String name, double x, double y) {
        this.name = name;
        this.location = new GeoJsonPoint(x, y);
    }

    public static HospitalDocument of(String name, double x, double y) {
        return new HospitalDocument(name, x, y);
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
