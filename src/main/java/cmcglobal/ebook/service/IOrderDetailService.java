package cmcglobal.ebook.service;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.model.request.OrderRequest;

public interface IOrderDetailService {
    ResponseData saveNewOrderDetail(OrderRequest orderRequest) throws ExceptionHandle;
}
