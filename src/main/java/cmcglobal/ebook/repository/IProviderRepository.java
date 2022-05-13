package cmcglobal.ebook.repository;

import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.model.response.ProviderResponse;
import cmcglobal.ebook.repository.impl.IProviderRepositoryExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Long> {
    Provider getProviderByCode(String code);
    Provider getProviderById(Long id);

//    @Query(value="SELECT pro.code, pro.name FROM PROVIDER pro WHERE pro.code like %?1% AND (pro.name) like %?2%", nativeQuery = true)
//    List<?> getAllProviderByConditions(String code, String name);

    @Query(value="SELECT pro.code, pro.name FROM PROVIDER pro WHERE pro.code like %?1% AND (pro.name) like %?2%", nativeQuery = true)
    List<ProviderResponse> getAllProviderByConditions(String code, String name);


    @Query(value="SELECT COUNT(bo.name)as number FROM PROVIDER pro join BOOK bo  on pro.id=bo.provider_id WHERE pro.code=?1", nativeQuery = true)
    int getQuantityOfBookByProvider(String name);


//    @Query(value="SELECT bo.name, sum(od.quantity)" +
//            "FROM PROVIDER pro" +
//            "join BOOK bo  " +
//            "on pro.id=bo.provider_id" +
//            "join order_detail od" +
//            "on bo.id= od.books_id" +
//            "WHERE pro.code=?1" +
//            "Group By bo.id" +
//            "order by sum(od.quantity)  desc" +
//            "limit 5;", nativeQuery = true)
//    List<?> getFiveBooksAreTopOrder(String name);


    @Query(value="SELECT bo.name, sum(od.quantity)FROM PROVIDER pro join BOOK bo on pro.id=bo.provider_id join order_detail od on bo.id= od.books_id  WHERE pro.code=?1 Group By bo.id order by sum(od.quantity)  desc limit 5;", nativeQuery = true)
    List<?> getFiveBooksAreTopOrder(String name);


}
