package cmcglobal.exambook.service;


import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Author;
import cmcglobal.exambook.exception.ExceptionHandle;

public interface IAuthorService{
    ResponseData save(Author author) throws ExceptionHandle;
    Author findById(Long id);
    ResponseData update(Author author) throws ExceptionHandle;
    int countBookInStock(Long id) throws ExceptionHandle;
    ResponseData deleteAuthor(Long id) throws ExceptionHandle;
    ResponseData getDataOfAuthor(Long id);
}
