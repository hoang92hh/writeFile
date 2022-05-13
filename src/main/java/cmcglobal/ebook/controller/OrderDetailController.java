package cmcglobal.ebook.controller;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.model.request.OrderRequest;
import cmcglobal.ebook.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @PostMapping("/add")
    public ResponseData addNewOrderDetail(@RequestBody OrderRequest orderRequest) throws ExceptionHandle {
        return orderDetailService.saveNewOrderDetail(orderRequest);
    }
}
