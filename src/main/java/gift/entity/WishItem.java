package gift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wish")
public class WishItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    protected WishItem() {}

    public WishItem(Long id, Product product, Integer quantity, Member member) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.member = member;
    }

    public WishItem(Product product, Integer quantity, Member member) {
        this.product = product;
        this.quantity = quantity;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "WishItem(" + id + ") - quantity: " + quantity;
    }

}
