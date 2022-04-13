package pt.api.stock_manager.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "work_sites")
@Proxy(lazy = false)
public class WorkSite {

    @Id
    @GeneratedValue(generator = "work_site_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "work_site_id_seq", sequenceName = "work_site_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    public WorkSite() {}

    public WorkSite(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkSite workSite = (WorkSite) o;
        return id == workSite.id && Objects.equals(name, workSite.name) && Objects.equals(location, workSite.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }
}
