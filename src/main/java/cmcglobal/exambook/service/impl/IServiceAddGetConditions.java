package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Provider;
import cmcglobal.exambook.service.IService;

public interface IServiceAddGetConditions<E> extends IService<E> {
    ResponseData getAllByRequest(E inputElement);
    ResponseData getAllResponseProvider(String[] codes);

    ResponseData writeFile(Provider providerRequest);
}
