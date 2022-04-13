package pt.api.stock_manager.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items_by_work_site")
@Proxy(lazy = false)
public class ItemByWorkSite {

    @Id
    @GeneratedValue(generator = "items_by_work_site_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "items_by_work_site_id_seq", sequenceName = "items_by_work_site_id_seq", allocationSize = 1)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_site_id", referencedColumnName = "id")
    private WorkSite workSite;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ItemByWorkSite() {

    }

    public ItemByWorkSite(Item item, WorkSite workSite, int quantity) {
        this.item = item;
        this.workSite = workSite;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public WorkSite getWorkSite() {
        return workSite;
    }

    public void setWorkSite(WorkSite workSite) {
        this.workSite = workSite;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemByWorkSite that = (ItemByWorkSite) o;
        return id == that.id && quantity == that.quantity && Objects.equals(item, that.item) && Objects.equals(workSite, that.workSite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, workSite, quantity);
    }
}
