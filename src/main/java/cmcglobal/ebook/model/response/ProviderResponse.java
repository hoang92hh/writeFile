package cmcglobal.ebook.model.response;

import java.util.List;

public class ProviderResponse {
    private String code;
    private String name;

    //thuoc tinh join table
    private int quantityOfBook;
    private List<?> nameBooks;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProviderResponse(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public ProviderResponse() {
    }

        public int getQuantityOfBook() {
        return quantityOfBook;
    }

    public void setQuantityOfBook(int quantityOfBook) {
        this.quantityOfBook = quantityOfBook;
    }

    public List<?> getNameBooks() {
        return nameBooks;
    }

    public void setNameBooks(List<?> nameBooks) {
        this.nameBooks = nameBooks;
    }
}
