package cmcglobal.ebook.service.impl;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.service.IService;

public interface IProviderService extends IService<Provider> {

    ResponseData getAllMultiCode(String[] codes);

    ResponseData getAllProivderByConditions(Provider providerRequest);

    ResponseData saveAll(Provider[] provider);

    ResponseData saveAllByHibernate(Provider[] provider);
}
