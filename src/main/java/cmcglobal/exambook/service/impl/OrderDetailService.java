package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.*;
import cmcglobal.exambook.exception.ExceptionHandle;
import cmcglobal.exambook.exception.ExceptionResponse;
import cmcglobal.exambook.model.request.BookOrderRequest;
import cmcglobal.exambook.model.request.OrderRequest;
import cmcglobal.exambook.repository.IBookRepository;
import cmcglobal.exambook.repository.IOrderDetailRepository;
import cmcglobal.exambook.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements  IOrderDetailService {

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Autowired
    private IBookRepository bookRepository;



    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;




    @Override
    public ResponseData saveNewOrderDetail(OrderRequest orderRequest)  {

        ResponseData responseData = new ResponseData();

        try {
            //validation order
            ExceptionResponse.checkExceptionOfOrderDetail(orderRequest);
            boolean check = checkQuantityOfBookInService(orderRequest.getBookOrderRequestList());
            if(!check){
                responseData.setMessage("The list of books with limited quantities is out");
                responseData.setCode("322");
                responseData.setStatus("ERROR");
                return responseData;
            }
            Customer checkCustomer = customerService.findCustomerByEmail(orderRequest.getCustomer().getEmail());

            Order order = new Order();
            order.setCustomer(orderRequest.getCustomer());

            if(checkCustomer == null){
                //validation customer
                ResponseData customer = customerService.add(orderRequest.getCustomer());
                Customer checkNewCustomer = customerService.findCustomerByEmail(orderRequest.getCustomer().getEmail());
                order.setCustomer(checkNewCustomer);
            }else {
                order.setCustomer(checkCustomer);
            }



            List<BookOrderRequest> bookList = orderRequest.getBookOrderRequestList();
            ResponseData newOrder = orderService.add(order);


            for (BookOrderRequest bookOrderRequest : bookList) {
                OrderDetail newOrderDetail = new OrderDetail();
                Book book = bookRepository.findByISBNCode(bookOrderRequest.getIsbnCode());

                newOrderDetail.setOrder(order);
                newOrderDetail.setBooks(book);
                Long quantityNewOrder = (long) bookOrderRequest.getQuantityOfBookOrder();

                newOrderDetail.setQuantity(quantityNewOrder);
                book.setQuantity(book.getQuantity() - quantityNewOrder);
                bookRepository.save(book);
                orderDetailRepository.save(newOrderDetail);

            }

            responseData.setCode("200");
            responseData.setStatus("SUCCESS");
            responseData.setMessage("ADDED");
        }
        catch (ExceptionHandle e){
            responseData.setMessage(e.getMessage());
            e.printStackTrace();
            responseData.setCode(e.getCode());
            responseData.setStatus("ERROR");
        } catch (Exception e){
            responseData.setMessage(e.getMessage());
            e.printStackTrace();
            responseData.setCode("400");
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    private boolean checkQuantityOfBookInService(List<BookOrderRequest> bookOrderRequest)  {
        boolean check= true;
        for (BookOrderRequest x : bookOrderRequest){
            Book book = bookRepository.findByISBNCode(x.getIsbnCode());
            if(book != null && !book.getStatus()){
                Long quantityNewOrder = (long) x.getQuantityOfBookOrder();
                if(book.getQuantity() < quantityNewOrder){
                    check = false;
                }
            }
        }
        return check;
    }
}
