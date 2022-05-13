package cmcglobal.ebook.repository;

import cmcglobal.ebook.entity.Author;
import cmcglobal.ebook.entity.Book;
import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.model.request.BookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select * from book b where b.isbncode = :isbn", nativeQuery = true)
    Book findByISBNCode(@Param("isbn") String isbn);
    List<Book> getBookByProvider(Provider provider);
    @Query(value = "select * from book b join book_author ba on b.id = ba.book_id join provider p on p.id = b.provider_id where b.status = false and b.name like %?1% and ba.author_id like %?2% and p.id like %?3% and b.isbncode like %?4% and b.price between ?5 and ?6 group by b.name order by b.name asc", nativeQuery = true)
    Page<Book> findAllByNameAndAndAuthorAndProviderAndPriceBetweenAndISBNCode
            (String name, String author, String provider_id, String isbn_code, Long price1, Long price2, Pageable pageable);

    List<Book> findBookByAuthor(Author oldAuthor);

    BookRequest findBookById(Long id);

}
