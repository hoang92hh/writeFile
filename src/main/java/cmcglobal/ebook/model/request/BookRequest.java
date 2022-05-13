package cmcglobal.ebook.model.request;

public class BookRequest {

    private String name;
    private String iSBNCode;
    private Long price;
    private Long quantity;
    private Long[] author;
    private Long provider;

    public BookRequest(String name, String iSBNCode, Long price, Long quantity, Long[] author, Long provider) {
        this.name = name;
        this.iSBNCode = iSBNCode;
        this.price = price;
        this.quantity = quantity;
        this.author = author;
        this.provider = provider;
    }

    public BookRequest() {
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

    public Long[] getAuthor() {
        return author;
    }

    public void setAuthor(Long[] author) {
        this.author = author;
    }

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }
}
