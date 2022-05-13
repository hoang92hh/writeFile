package cmcglobal.ebook.model.request;

public class BookSearch {

    private String name;
    private String iSBNCode;
    private Long price1 = 1L;
    private Long price2 = 99999L;
    private String author;
    private String provider;

    public BookSearch() {
    }

    public BookSearch(String name, String iSBNCode, Long price1, Long price2, String author, String provider) {
        this.name = name;
        this.iSBNCode = iSBNCode;
        this.price1 = price1;
        this.price2 = price2;
        this.author = author;
        this.provider = provider;
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

    public Long getPrice1() {
        return price1;
    }

    public void setPrice1(Long price1) {
        this.price1 = price1;
    }

    public Long getPrice2() {
        return price2;
    }

    public void setPrice2(Long price2) {
        this.price2 = price2;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
