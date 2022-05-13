package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Customer;
import cmcglobal.exambook.entity.Provider;
import cmcglobal.exambook.exception.ExceptionHandle;
import cmcglobal.exambook.exception.ExceptionResponse;
import cmcglobal.exambook.repository.ICustomerRepository;
import cmcglobal.exambook.service.ICustomerService;
import cmcglobal.exambook.service.IService;
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
