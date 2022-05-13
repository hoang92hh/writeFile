package cmcglobal.exambook.controller;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Author;
import cmcglobal.exambook.exception.ExceptionHandle;
import cmcglobal.exambook.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @PostMapping("/add")
    public ResponseData addNewAuthor(@RequestBody Author author) throws ExceptionHandle {
        return authorService.save(author);
    }

    @PostMapping("/update")
    public ResponseData updateAuthor(@RequestBody Author author) throws ExceptionHandle {
        return authorService.update(author);
    }

    @DeleteMapping("/delete")
    public ResponseData deleteAuthor(@Param("id") Long id) throws ExceptionHandle {
        return authorService.deleteAuthor(id);
    }

    @GetMapping("/detail")
    public ResponseData getAuthorDetail(@Param("id") Long id){
        return authorService.getDataOfAuthor(id);
    }
}
