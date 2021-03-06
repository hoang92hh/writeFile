package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Order;
import cmcglobal.exambook.entity.Provider;
import cmcglobal.exambook.repository.IOrderRepository;
import cmcglobal.exambook.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IService<Order> {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Override
    public ResponseData findAll() {
        return null;
    }

    @Override
    public ResponseData findById(Long id) {
        return null;
    }

    @Override
    public ResponseData getBookOfProvider(String name) {
        return null;
    }

    @Override
    public ResponseData add(Order elementInput) {

        ResponseData responseData = new ResponseData();
        try {
            iOrderRepository.save(elementInput);
            responseData.setData(elementInput);
            responseData.setCode("201");
            responseData.setMessage("SUCCESS");
            responseData.setStatus("ADDED");
        }catch (Exception e){
            responseData.setMessage(e.getMessage());
            e.printStackTrace();
            responseData.setCode("400");
            responseData.setStatus("ERROR");
        }
        return responseData;
    }

    @Override
    public ResponseData delete(Long id) {
        return null;
    }

    @Override
    public ResponseData changeStatus(Long id) {
        return null;
    }

    @Override
    public ResponseData update(Provider provider) {
        return null;
    }
}
