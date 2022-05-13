package cmcglobal.exambook.service;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.exception.ExceptionHandle;
import cmcglobal.exambook.model.request.OrderRequest;

public interface IOrderDetailService {
    ResponseData saveNewOrderDetail(OrderRequest orderRequest) throws ExceptionHandle;
}
