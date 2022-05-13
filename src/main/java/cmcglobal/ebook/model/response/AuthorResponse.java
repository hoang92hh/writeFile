package cmcglobal.ebook.model.response;

import cmcglobal.ebook.entity.Book;

import java.util.List;

public class AuthorResponse {

    private String name;
    private int countOfBook;
    private List<BookResponse> books;

    public AuthorResponse() {
    }

    public AuthorResponse(String name, int countOfBook, List<BookResponse> books) {
        this.name = name;
        this.countOfBook = countOfBook;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfBook() {
        return countOfBook;
    }

    public void setCountOfBook(int countOfBook) {
        this.countOfBook = countOfBook;
    }

    public List<BookResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponse> books) {
        this.books = books;
    }
}
