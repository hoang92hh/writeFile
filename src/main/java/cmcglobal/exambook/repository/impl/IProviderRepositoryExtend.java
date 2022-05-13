package cmcglobal.exambook.repository.impl;

import cmcglobal.exambook.entity.Provider;

import java.util.List;

public interface IProviderRepositoryExtend {
    List<Provider> findProviderByCodesList(String stringQuery);
    void saveAllProviderByHibernate(Provider[] providerList);
}
