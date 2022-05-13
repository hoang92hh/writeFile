package cmcglobal.ebook.service.impl;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.service.IService;

import java.util.List;

public interface IServiceAddGetConditions<E> extends IService<E> {
    ResponseData getAllByRequest(E inputElement);
    ResponseData getAllResponseProvider(String[] codes);
}
