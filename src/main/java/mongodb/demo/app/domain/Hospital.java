package mongodb.demo.app.domain;

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

    protected Hospital() {
    }

    private Hospital(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Hospital ofName(String name) {
        return new Hospital(null, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
