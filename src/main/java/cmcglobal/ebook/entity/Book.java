package cmcglobal.ebook.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String iSBNCode;
    private Long price;
    private Long quantity;
    private Boolean status = false;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Author> author;
    @ManyToOne
    private Provider provider;

    public Book(Long id, String name, String iSBNCode, Long price, Long quantity, Boolean status, Set<Author> author, Provider provider) {
        this.id = id;
        this.name = name;
        this.iSBNCode = iSBNCode;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.author = author;
        this.provider = provider;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getiSBNCode() {
        return iSBNCode;
    }

    public void setiSBNCode(String iSBNCode) {
        this.iSBNCode = iSBNCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
