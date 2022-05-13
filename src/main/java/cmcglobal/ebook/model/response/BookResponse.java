package cmcglobal.ebook.model.response;

import cmcglobal.ebook.entity.Provider;

public class BookResponse {

    private String name;
    private String iSBNCode;
    private Long price;
    private int countBook;
    private Provider provider;
    private int total;

    public BookResponse() {
    }

    public BookResponse(String name, String iSBNCode, Long price, int countBook, Provider provider, int total) {
        this.name = name;
        this.iSBNCode = iSBNCode;
        this.price = price;
        this.countBook = countBook;
        this.provider = provider;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCountBook() {
        return countBook;
    }

    public void setCountBook(int countBook) {
        this.countBook = countBook;
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
