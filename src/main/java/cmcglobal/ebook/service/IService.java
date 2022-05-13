package cmcglobal.ebook.service;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.exception.ExceptionHandle;

import java.util.List;

public interface IService<E>  {
    ResponseData findAll();
    ResponseData findById(Long id);
    ResponseData findByCode(String name);


    ResponseData add(E elemenInput) throws ExceptionHandle;

    ResponseData delete(Long id);
    ResponseData changeStatus(Long id);

    ResponseData update(Provider provider);
}
