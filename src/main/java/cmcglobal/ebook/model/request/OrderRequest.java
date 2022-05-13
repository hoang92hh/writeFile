package cmcglobal.ebook.model.request;

import cmcglobal.ebook.entity.Customer;

import java.util.List;

public class OrderRequest {

    private List<BookOrderRequest> bookOrderRequestList;
    private Customer customer;

    public OrderRequest() {
    }

    public OrderRequest(List<BookOrderRequest> bookOrderRequestList, Customer customer) {
        this.bookOrderRequestList = bookOrderRequestList;
        this.customer = customer;
    }

    public List<BookOrderRequest> getBookOrderRequestList() {
        return bookOrderRequestList;
    }

    public void setBookOrderRequestList(List<BookOrderRequest> bookOrderRequestList) {
        this.bookOrderRequestList = bookOrderRequestList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
