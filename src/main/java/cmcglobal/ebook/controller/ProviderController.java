package cmcglobal.ebook.controller;

import cmcglobal.ebook.common.ResponseData;
import cmcglobal.ebook.entity.Book;
import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.exception.ExceptionHandle;
import cmcglobal.ebook.model.request.ProviderRequest;
import cmcglobal.ebook.service.IService;
import cmcglobal.ebook.service.impl.IServiceAddGetConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/provider")
public class ProviderController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    IServiceAddGetConditions providerService ;

    @GetMapping(value="/getAll")
    public ResponseData getAllProvider(){
        return providerService.findAll();
    }

    @GetMapping(value="/getById/{id}")
    public ResponseData getProviderById(@PathVariable Long id){
      return providerService.findById(id);

    }


    @PostMapping(value="/addProvider")
    public ResponseData addProvider(@RequestBody Provider provider) throws ExceptionHandle {
       return providerService.add(provider);
    }

    @PutMapping(value="/changeStatus/{id}")
    public ResponseData changeStatus(@PathVariable Long id){
        return providerService.changeStatus(id);

    }

    @PutMapping(value="/update")
    public ResponseData updateProvider(@RequestBody Provider provider){
       return providerService.update(provider);

    }

    @DeleteMapping(value = "/delete")
    public ResponseData deleteProvider(@Param("id") Long id){
       return  providerService.delete(id);

    }

    @GetMapping(value="/getBookOfProvider")
    public ResponseData getBookOfProvider(@Param("code") String code){
        return providerService.findByCode(code);
    }




    @GetMapping(value="/getByRequest")
    public ResponseData getProviderByRequest(@RequestBody Provider providerRequest){
        return providerService.getAllByRequest(providerRequest);
    }
//
//    @GetMapping(value="/getAllByMultipleData")
//    public ResponseData getProviderByRequest(String[] nxbCode){
//        return providerService.getAllByRequest(nxbCode);
//    }




}
