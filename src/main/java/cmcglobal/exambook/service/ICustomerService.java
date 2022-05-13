package cmcglobal.exambook.service;

import cmcglobal.exambook.entity.Customer;

public interface ICustomerService {
    Customer findCustomerByEmail(String email);
}
