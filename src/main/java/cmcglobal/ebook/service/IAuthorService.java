package cmcglobal.ebook.service;


import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.entity.Author;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.model.response.BookResponse;

import java.util.List;

public interface IAuthorService{
    ResponseData save(Author author) throws ExceptionHandle;
    Author findById(Long id);
    ResponseData update(Author author) throws ExceptionHandle;
    int countBookInStock(Long id) throws ExceptionHandle;
    ResponseData deleteAuthor(Long id) throws ExceptionHandle;
    ResponseData getDataOfAuthor(Long id);
}
