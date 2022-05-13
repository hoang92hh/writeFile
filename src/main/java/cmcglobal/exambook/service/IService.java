package cmcglobal.exambook.service;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Provider;
import cmcglobal.exambook.exception.ExceptionHandle;

public interface IService<E>  {
    ResponseData findAll();
    ResponseData findById(Long id);
    ResponseData getBookOfProvider(String name);


    ResponseData add(E elemenInput) throws ExceptionHandle;

    ResponseData delete(Long id);
    ResponseData changeStatus(Long id);

    ResponseData update(Provider provider);
}
