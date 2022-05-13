package cmcglobal.exambook.repository;

import cmcglobal.exambook.entity.Customer;
import cmcglobal.exambook.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByCustomer(Customer customer);
}
