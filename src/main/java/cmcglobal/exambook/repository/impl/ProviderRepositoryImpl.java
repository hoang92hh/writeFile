//package cmcglobal.ebook.repository.impl;
//
//import cmcglobal.ebook.entity.Provider;
//import cmcglobal.ebook.model.response.ProviderResponse;
//import cmcglobal.ebook.repository.IProviderRepository;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//
//public class ProviderRepositoryImpl implements IProviderRepositoryExtend {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    @Override
//    public List<Provider> getAllResponseProvider(String[] nxbCode) {
////        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
////        CriteriaQuery<Provider> query = cb.createQuery(Provider.class);
////        Root<Provider> responseRoot = query.from(Provider.class);
////
////        Path<String> emailPath = responseRoot.get("code");
////
////        List<Predicate> predicates = new ArrayList<>();
////        for (String code : nxbCode) {
////            predicates.add(cb.like(emailPath, code));
////        }
////        query.select(responseRoot)
////                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
////
////
////        return entityManager.createQuery(query)
////                .getResultList();
//
//        List<Provider> list = new ArrayList<>();
//        list.add(new Provider());
//        list.add(new Provider());
//        return list;
//    }
//}
