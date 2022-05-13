package cmcglobal.ebook.service.impl;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.entity.Author;
import cmcglobal.ebook.entity.Book;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.exception.ExceptionResponse;
import cmcglobal.ebook.model.response.AuthorResponse;
import cmcglobal.ebook.model.response.BookResponse;
import cmcglobal.ebook.repository.IAuthorRepository;
import cmcglobal.ebook.repository.IBookRepository;
import cmcglobal.ebook.service.IAuthorService;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private IAuthorRepository authorRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);


    @Autowired
    private IBookRepository bookRepository;

    @Override
    public ResponseData save(Author author) {
        ResponseData responseData = new ResponseData();
        try {
            ExceptionResponse.checkExceptionAuthor(author);
            Author newAuthor = authorRepository.save(author);
            responseData.setData(newAuthor);
            responseData.setMessage("CREATED");
            responseData.setStatus("SUCCESS");
            responseData.setCode("200");
        }catch (ExceptionHandle e){
            log.error(e.getMessage());
            e.printStackTrace();
            responseData.setMessage(e.getMessage());
            responseData.setCode(e.getCode());
            responseData.setStatus("ERROR");
        } catch (Exception e){
            e.printStackTrace();
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findAuthorById(id);
    }

    @Override
    public ResponseData update(Author author) {
        ResponseData responseData = new ResponseData();
        try {
            ExceptionResponse.checkExceptionAuthor(author);

            Author newAuthor = findById(author.getId());
            if (newAuthor != null){
                newAuthor.setId(author.getId());
                newAuthor.setName(author.getName());
                authorRepository.save(newAuthor);
                responseData.setData(newAuthor);
                responseData.setStatus("UPDATED");
                responseData.setCode("200");
                responseData.setMessage("SUCCESS");
            }
        } catch (ExceptionHandle e){
            log.error(e.getMessage());
            e.printStackTrace();
            responseData.setMessage(e.getMessage());
            responseData.setCode(e.getCode());
            responseData.setStatus("ERROR");
        } catch (Exception e){
            e.printStackTrace();
            responseData.setCode("404");
            responseData.setMessage(e.getMessage());
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    @Override
    public int countBookInStock(Long id) {
        return authorRepository.countBookInStock(id);
    }

    @Override
    public ResponseData deleteAuthor(Long id) throws ExceptionHandle {
        ResponseData responseData = new ResponseData();
        try {
            Author oldAuthor = findById(id);
            List<Book> book = bookRepository.findBookByAuthor(oldAuthor);
            if(oldAuthor != null && book.isEmpty() ){
                    authorRepository.deleteById(id);
                    responseData.setData(oldAuthor);
                    responseData.setStatus("DELETED");
                    responseData.setCode("200");
                    responseData.setMessage("SUCCESS");
            } else  throw new HandlerException("Cant delete this author cause his book still available on your store", "400");

        } catch (Exception e){
            e.printStackTrace();
            responseData.setCode("404");
            responseData.setMessage(e.getMessage());
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    @Override
    public ResponseData getDataOfAuthor(Long id) {
        ResponseData responseData = new ResponseData();
        try {
            Author oldAuthor = findById(id);
            if(oldAuthor != null){
                List<?> bookResponses = authorRepository.getDataOfAuthor(id);
                int count = countBookInStock(id);
                AuthorResponse authorResponse = new AuthorResponse();
                authorResponse.setName(oldAuthor.getName());
                authorResponse.setCountOfBook(count);
                authorResponse.setBooks((List<BookResponse>) bookResponses);
                responseData.setData(authorResponse);
                responseData.setStatus("OK");
                responseData.setCode("200");
                responseData.setMessage("SUCCESS");
            } else throw new HandlerException("This id isn't exist", "404");
        }catch (Exception e){
            e.printStackTrace();
            responseData.setCode("404");
            responseData.setMessage(e.getMessage());
            responseData.setStatus("ERROR");
        }
        return responseData;

    }
}
