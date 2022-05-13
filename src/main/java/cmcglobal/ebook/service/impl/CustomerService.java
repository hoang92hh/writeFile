package cmcglobal.ebook.service.impl;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.entity.Customer;
import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.exception.ExceptionResponse;
import cmcglobal.ebook.repository.ICustomerRepository;
import cmcglobal.ebook.service.ICustomerService;
import cmcglobal.ebook.service.IService;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements IService<Customer>, ICustomerService {
    @Autowired
    public ICustomerRepository customerRepository;

    @Override
    public ResponseData findAll() {
        return null;
    }

    @Override
    public ResponseData findById(Long id) {
        return null;
    }

    @Override
    public ResponseData findByCode(String name) {
        return null;
    }

    @Override
    public ResponseData add(Customer elementInput) throws ExceptionHandle {
        ResponseData responseData = new ResponseData();
        ExceptionResponse.checkExceptionOfCustomer(elementInput);
        Customer customer = customerRepository.findCustomerByEmail(elementInput.getEmail());
            if(customer == null) {
                customerRepository.save(elementInput);
                responseData.setData(elementInput);
                responseData.setCode("200");
                responseData.setMessage("SUCCESS");
                responseData.setStatus("ADDED");
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

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
