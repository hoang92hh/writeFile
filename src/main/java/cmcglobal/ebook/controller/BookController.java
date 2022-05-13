package cmcglobal.ebook.controller;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.entity.Book;
import cmcglobal.ebook.model.request.BookRequest;
import cmcglobal.ebook.model.request.BookSearch;
import cmcglobal.ebook.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/book")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private IBookService serviceBook;


    @PostMapping("/create-book")
    public ResponseData createBook(@RequestBody BookRequest book) {
        ResponseData responseData = new ResponseData();
        try {
            Book bookResult = serviceBook.saveBook(book);
            responseData.setData(bookResult);
            responseData.setMessage("CREATED");
            responseData.setStatus("SUCCESS");
            responseData.setCode("200");
        } catch (ExceptionHandle e) {
            log.error(e.getMessage());
            e.printStackTrace();
            responseData.setMessage(e.getMessage());
            responseData.setCode(e.getCode());
            responseData.setStatus("ERROR");
        }catch (Exception e) {
            e.printStackTrace();
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    @PutMapping("/update-book")
    public ResponseData updateBook(@RequestBody BookRequest bookRequest){
        ResponseData responseData = new ResponseData();
        try {
            Book book = serviceBook.updateBook(bookRequest);
            responseData.setData(book);
            responseData.setMessage("UPDATED");
            responseData.setStatus("SUCCESS");
            responseData.setCode("201");
        } catch (ExceptionHandle e) {
            log.error(e.getMessage());
            e.printStackTrace();
            responseData.setMessage(e.getMessage());
            responseData.setCode(e.getCode());
            responseData.setStatus("ERROR");
        }catch (Exception e) {
            e.printStackTrace();
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    @DeleteMapping("/delete-book")
    public ResponseData deleteBook(@Param("id") Long id){
        ResponseData responseData = new ResponseData();
        try {
            serviceBook.deleteBook(id);
            responseData.setMessage("DELETED");
            responseData.setStatus("SUCCESS");
            responseData.setCode("202");
        }catch (ExceptionHandle e){
            log.error(e.getMessage());
            e.printStackTrace();
            responseData.setMessage(e.getMessage());
            responseData.setCode(e.getCode());
            responseData.setStatus("ERROR");
        }catch (Exception e) {
            e.printStackTrace();
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    @PostMapping("/search")
    public ResponseData searchBook(@RequestBody BookSearch bookSearch, Pageable pageable) {
        ResponseData responseData = new ResponseData();
        try {
            Page<Book> book = serviceBook.findAllByNameAndAndAuthorAndProviderAndPriceBetweenAndISBNCode
                    (bookSearch.getName(), bookSearch.getAuthor(), bookSearch.getProvider(), bookSearch.getiSBNCode(), bookSearch.getPrice1(), bookSearch.getPrice2(), pageable);
            responseData.setData(book);
            responseData.setMessage("COMPLETE");
            responseData.setStatus("SUCCESS");
            responseData.setCode("203");
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setStatus("ERROR");
        }
        return responseData;
    }
}
