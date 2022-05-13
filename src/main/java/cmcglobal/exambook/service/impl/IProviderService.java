package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Provider;
import cmcglobal.exambook.service.IService;

public interface IProviderService extends IService<Provider> {

    ResponseData getAllMultiCode(String[] codes);

    ResponseData getAllProivderByConditions(Provider providerRequest);

    ResponseData saveAll(Provider[] provider);

    ResponseData saveAllByHibernate(Provider[] provider);

    ResponseData writeFile(Provider providerRequest);
}
