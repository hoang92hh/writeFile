package cmcglobal.ebook.exception;

import cmcglobal.ebook.entity.Customer;
import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.entity.Author;
import cmcglobal.ebook.entity.Book;
import cmcglobal.ebook.model.request.BookOrderRequest;
import cmcglobal.ebook.model.request.BookRequest;
import cmcglobal.ebook.model.request.OrderRequest;

public class ExceptionResponse {
    public static void checkExceptionOfBook(BookRequest book) throws ExceptionHandle{


        if (book.getName() == null) {
            throw new ExceptionHandle("Name cannot be null", "000");
        }
        if (book.getName().isEmpty()) {
            throw new ExceptionHandle("Name cannot be empty", "111");
        }
        if (book.getPrice() == null) {
            throw new ExceptionHandle("Price cannot be null", "112");
        }
        if (book.getQuantity() == null) {
            throw new ExceptionHandle("Quantity cannot be null", "115");
        }
        if (book.getiSBNCode() == null) {
            throw new ExceptionHandle("ISBN code cannot be null", "116");
        }
        if (book.getiSBNCode().isEmpty()) {
            throw new ExceptionHandle("ISBN code cannot be empty", "117");
        }
        if (book.getProvider()== null ){
            throw new ExceptionHandle("Provider cannot be null", "118" );
        }
        if (book.getAuthor() == null){
            throw new ExceptionHandle("Author cannot be null", "113");
        }
    }

    public static void checkExceptionOfProvider(Provider provider) throws ExceptionHandle{
        if(provider.getCode()==null){
            throw new ExceptionHandle("Provider Code not null", "000");
        }
        if(provider.getName().isEmpty()){
            throw new ExceptionHandle("Provider Name not empty", "000");
        }


    }

    public static void checkExceptionIsExist(String id, Object object) throws ExceptionHandle{

        if(id.isEmpty()){
            throw new ExceptionHandle("id is not emty", "000");
        }
        if(object==null){
            throw new ExceptionHandle("Object is not exist", "000");
        }
    }


    public static void checkExceptionAuthor(Author author) throws ExceptionHandle{
        if (author.getName() == null) {
                throw new ExceptionHandle("Name cannot be null", "000");
        }
        if (author.getName().isEmpty()) {
                throw new ExceptionHandle("Name cannot be empty", "111");
        }
//        if(author.getId() == null){
//            throw new ExceptionHandle("Cant find author id", "404");
//        }
    }

    public static void checkExceptionOfOrderDetail(OrderRequest orderRequest) throws ExceptionHandle{
        if(orderRequest.getBookOrderRequestList() == null || orderRequest.getBookOrderRequestList().isEmpty()){
            throw new ExceptionHandle("ISBN Code can not null or empty", "000");
        }
        if(orderRequest.getCustomer() == null){
            throw new ExceptionHandle("Customer can not be null", "023");
        }

    }

    public static void checkExceptionOfCustomer(Customer customer) throws ExceptionHandle {
        if(customer == null){
            throw new ExceptionHandle("Customer can not be null", "000");
        }
        if(customer.getEmail()==null && customer.getEmail().equals("")){
            throw new ExceptionHandle("Customer can not be empty", "000");
        }
        if(!customer.getEmail().matches("^[a-zA-Z0-9]*@[a-zA-Z]*.{2,6}$")){
            throw new ExceptionHandle("Email must like 'example69@gmail.com'", "000");
        }
    }

    public static void checkExceptionOfBookOrderRequest(BookOrderRequest bookOrderRequest) throws ExceptionHandle {
        if(bookOrderRequest.getIsbnCode() == null){
            throw new ExceptionHandle("ISBN code can not be null", "000");
        }
        if(bookOrderRequest.getQuantityOfBookOrder() == 0){
            throw new ExceptionHandle("Quantity can not be 0", "000");
        }
    }
}
