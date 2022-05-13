package cmcglobal.ebook.service.impl;


import cmcglobal.ebook.entity.Author;
import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.entity.Book;
import cmcglobal.ebook.exception.ExceptionResponse;
import cmcglobal.ebook.model.request.BookRequest;
import cmcglobal.ebook.repository.IAuthorRepository;
import cmcglobal.ebook.repository.IBookRepository;
import cmcglobal.ebook.repository.IProviderRepository;
import cmcglobal.ebook.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService implements IBookService {
    @Autowired
    public IBookRepository bookRepository;

    @Autowired
    public IAuthorRepository authorRepository;

    @Autowired
    public IProviderRepository providerRepository;

    @Override
    public Book saveBook(BookRequest book) throws ExceptionHandle {
        ExceptionResponse.checkExceptionOfBook(book);
        Book newBook = new Book();
        newBook.setName(book.getName());
        newBook.setiSBNCode(book.getiSBNCode());
        newBook.setPrice(book.getPrice());
        newBook.setQuantity(book.getQuantity());

        insertNewAuthor(book, newBook);

        if(book.getProvider() != null){
            Provider provider = providerRepository.getProviderById(book.getProvider());
            if(provider != null){
                newBook.setProvider(provider);

            }
        }

        String isbn = newBook.getiSBNCode();
        Book checkBook = bookRepository.findByISBNCode(isbn);
        if(checkBook != null){
            checkBook.setName(book.getName());
            checkBook.setPrice(book.getPrice());
            checkBook.setQuantity(book.getQuantity() + checkBook.getQuantity());
            return bookRepository.save(checkBook);
        }
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(Long id) throws ExceptionHandle {
        BookRequest bookRequest = bookRepository.findBookById(id);
        if(bookRequest != null){
            String isbn = bookRequest.getiSBNCode();
            Book checkBook = bookRepository.findByISBNCode(isbn);
            if (checkBook == null) {
                throw new ExceptionHandle("Isbn code does not exist","404");
            }else {
                checkBook.setStatus(true);
                bookRepository.save(checkBook);
            }
        }
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book updateBook(BookRequest book) throws ExceptionHandle{
        ExceptionResponse.checkExceptionOfBook(book);
        String isbn = book.getiSBNCode();
        Book checkBook = bookRepository.findByISBNCode(isbn);
        if (checkBook == null) {
            throw new ExceptionHandle("Isbn code does not exist","404");
        }else {
            checkBook.setName(book.getName());
            checkBook.setPrice(book.getPrice());
            checkBook.setQuantity(book.getQuantity() + checkBook.getQuantity());

            insertNewAuthor(book, checkBook);

            if(book.getProvider() != null){
                Provider provider = providerRepository.getProviderById(book.getProvider());
                if(provider != null){
                    checkBook.setProvider(provider);

                }
            }

            return bookRepository.save(checkBook);
        }
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Book findBookByISBNCode(String isbnCode) {
        return bookRepository.findByISBNCode(isbnCode);
    }

    @Override
    public Page<Book> findAllByNameAndAndAuthorAndProviderAndPriceBetweenAndISBNCode
            (String name, String author, String provider_id, String isbn_code, Long price1, Long price2, Pageable pageable) {
        return bookRepository.findAllByNameAndAndAuthorAndProviderAndPriceBetweenAndISBNCode
                (name, author, provider_id, isbn_code, price1, price2, pageable);
    }

    public void insertNewAuthor(BookRequest book, Book checkBook){
        if(book.getAuthor() != null){
            Set<Author> authorSet = new HashSet<>();
            for (int i=0; i<book.getAuthor().length; i++){
                Author author = authorRepository.findAuthorById(book.getAuthor()[i]);
                if(author != null){
                    authorSet.add(author);
                }
            }
            checkBook.setAuthor(authorSet);
        }
    }

}
