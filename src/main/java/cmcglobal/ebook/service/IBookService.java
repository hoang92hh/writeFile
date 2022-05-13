package cmcglobal.ebook.service;


import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.entity.Book;
import cmcglobal.ebook.model.request.BookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IBookService {
     Book saveBook(BookRequest book) throws ExceptionHandle;
     void deleteBook(Long id) throws ExceptionHandle;
     List<Book> getAll();
     Book updateBook (BookRequest book) throws ExceptionHandle;
     Book getBookById (Long id);
     Book findBookByISBNCode(String isbnCode);
     Page<Book> findAllByNameAndAndAuthorAndProviderAndPriceBetweenAndISBNCode(String name, String author, String provider_id, String isbn_code, Long price1, Long price2, Pageable pageable);
}
