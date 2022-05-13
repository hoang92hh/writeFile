package cmcglobal.exambook.entity;

import cmcglobal.exambook.common.AbstracClassCommon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Array;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Provider  extends AbstracClassCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique=true)
    private String code;
    private String name;

    private Boolean status = true;


    @JsonIgnore
    @OneToMany(mappedBy = "provider")
    private Set<Book> books;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Provider(Long id, String name, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Provider(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Provider() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus() {
        this.status = !this.status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String[] getProperty() {
        String[]  propertyList= {"code", "name"};

        return propertyList;
    }

    @Override
    public String[] getValueOfObject() {
        String[]  value= {getCode(), getName()};
        return value;
    }

    public Provider(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
