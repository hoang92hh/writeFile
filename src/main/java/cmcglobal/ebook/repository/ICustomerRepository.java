package cmcglobal.ebook.repository;

import cmcglobal.ebook.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByEmail(String email);
}
