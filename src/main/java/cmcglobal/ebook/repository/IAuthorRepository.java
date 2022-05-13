package cmcglobal.ebook.repository;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.entity.Author;
import cmcglobal.ebook.model.response.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorById(Long id);

    @Query(value = "select count(ba.book_id) from author a join book_author ba on a.id = ba.author_id where a.id = ?1", nativeQuery = true)
    int countBookInStock(Long id);

    @Query(value = "select b.name as bookName,b.isbncode as bookISBN,b.price as bookPrice, p.name as providerName, sum(od.quantity) as total11 from author a join book_author ba on a.id = ba.author_id join book b on b.id = ba.book_id join order_detail od on ba.book_id = od.books_id join provider p on p.id = b.provider_id where a.id = :checkId group by b.name order by total11 DESC limit 3", nativeQuery = true)
    List<?> getDataOfAuthor(@Param("checkId") Long id);
}