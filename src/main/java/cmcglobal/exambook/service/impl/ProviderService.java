package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.AbstracClassCommon;
import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Book;
import cmcglobal.exambook.entity.Provider;
import cmcglobal.exambook.exception.ExceptionHandle;
import cmcglobal.exambook.exception.ExceptionResponse;
import cmcglobal.exambook.model.response.Iprovider;
import cmcglobal.exambook.model.response.ProviderResponse;
import cmcglobal.exambook.repository.IBookRepository;
import cmcglobal.exambook.repository.IProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService implements IServiceAddGetConditions <Provider> {

    @Autowired

    IProviderRepository providerRepository;

    @Autowired
    IBookRepository bookRepository;



    @Override
    public ResponseData findAll() {
        ResponseData responseData = new ResponseData();
        List<Provider> providerList = providerRepository.findAll();
        responseData.setData(providerList);
        responseData.setMessage("FindAll");
        responseData.setStatus("Success");
        responseData.setCode("200");
        return responseData;

    }

    @Override
    public ResponseData findById(Long id) {
        ResponseData responseData = new ResponseData();
        Optional<Provider> provider = providerRepository.findById(id);
        if(provider.isPresent()){
            responseData.setData(provider);
            responseData.setMessage("Find ProviderById");
            responseData.setStatus("Success");
            responseData.setCode("200");
        }
        else{
            responseData.setMessage("Not Found");
            responseData.setStatus("fail");
            responseData.setCode("200-1");
        }

        return responseData;
    }

    @Override
    public ResponseData findByCode(String name) {
        ResponseData responseData = new ResponseData();
        Provider provider = providerRepository.getProviderByCode(name);


        if(provider!=null){
            ProviderResponse providerResponse = new ProviderResponse();
            int number= providerRepository.getQuantityOfBookByProvider(name);
            List<?> books = providerRepository.getFiveBooksAreTopOrder(name);


            providerResponse.setCode(provider.getCode());
            providerResponse.setName(provider.getName());
            providerResponse.setQuantityOfBook(number);
            providerResponse.setNameBooks(books);


            responseData.setData(providerResponse);
            responseData.setMessage("Find 5 book are Top Order");
            responseData.setStatus("Success");
            responseData.setCode("200");
        }
        else{
            responseData.setMessage("Not Found");
            responseData.setStatus("fail");
            responseData.setCode("200-1");
        }

        return responseData;
    }




    @Override
    public ResponseData add(Provider elemenInput)  {
        ResponseData responseData = new ResponseData();
        try {
            ExceptionResponse.checkExceptionOfProvider(elemenInput);
            if(providerRepository.getProviderByCode(elemenInput.getCode())==null){
                providerRepository.save(elemenInput);
                responseData.setData(elemenInput);
                responseData.setMessage("add Provider");
                responseData.setStatus("Success");
                responseData.setCode("200");
            }
            else{
                responseData.setData(elemenInput);
                responseData.setMessage("Provider is exits");
                responseData.setStatus("fail");
                responseData.setCode("200-1");
            }

        }
        catch(ExceptionHandle e){
            responseData.setData(elemenInput);
            responseData.setMessage(e.getMessage());
            responseData.setCode(e.getCode());
            responseData.setStatus("Error");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return responseData;
    }

    @Override
    public ResponseData delete(Long id) {
        ResponseData responseData = new ResponseData();
        Provider provider = providerRepository.getById(id);
        List<Book> books = bookRepository.getBookByProvider(provider);

        if(provider !=null && books.isEmpty()){
            providerRepository.delete(provider);
            responseData.setData(provider);
            responseData.setMessage("DELETE");
            responseData.setStatus("Success");
            responseData.setCode("200");
        }
        else{
            responseData.setMessage("Not Found");
            responseData.setStatus("fail");
            responseData.setCode("200-1");
        }

        return responseData;

    }

    @Override
    public ResponseData changeStatus(Long id) {
        ResponseData responseData = new ResponseData();
        Provider provider = providerRepository.getProviderById(id);
        if(provider !=null) {
            provider.setStatus();
            providerRepository.save(provider);
            responseData.setData(provider);
            responseData.setMessage("Change Status");
            responseData.setStatus("Success");
            responseData.setCode("200");
        }

        else{
            responseData.setMessage("Object is not exits");
            responseData.setStatus("fail");
            responseData.setCode("200-1");
        }

        return responseData;
    }

    @Override
    public ResponseData update(Provider provider) {
        ResponseData responseData = new ResponseData();
        try {
            ExceptionResponse.checkExceptionOfProvider(provider);
            if(provider.getId()!=null){
                providerRepository.save(provider);
                responseData.setData(provider);
                responseData.setMessage("update Provider");
                responseData.setStatus("Success");
                responseData.setCode("200");
            }
            else{
                responseData.setData(provider);
                responseData.setMessage("Primary Key is not exits");
                responseData.setStatus("fail");
                responseData.setCode("200-1");
            }

        }
        catch(ExceptionHandle e){
            responseData.setData(provider);
            responseData.setMessage(e.getMessage());
            responseData.setCode(e.getCode());
            responseData.setStatus("Error");
        }

        catch (Exception e) {
            e.printStackTrace();
            responseData.setMessage(e.getMessage());
            responseData.setStatus("Error");
        }

        return responseData;
    }


    @Override
    public ResponseData getAllByRequest(Provider inputElement) {
        ResponseData responseData = new ResponseData();
        List<Iprovider> providerList = providerRepository.getAllProviderByConditions(inputElement.getCode(), inputElement.getName());
        responseData.setData(providerList);
        responseData.setMessage("FindAllByConditions");
        responseData.setStatus("Success");
        responseData.setCode("200");
        return responseData;
    }

    @Override
    public ResponseData getAllResponseProvider(String[] codes) {
        ResponseData responseData = new ResponseData();
//        List<?> providerList = providerRepository.getAllResponseProvider(codes);
//        responseData.setData(providerList);
        responseData.setMessage("FindAllByConditions");
        responseData.setStatus("Success");
        responseData.setCode("200");
        return responseData;
    }

    @Override
    public ResponseData writeFile(Provider inputElement) {
        ResponseData responseData = new ResponseData();
        List<Iprovider> providerList = providerRepository.getAllProviderByConditions(inputElement.getCode(), inputElement.getName());
        List<Provider> providers = new ArrayList<>();
        for (Iprovider p: providerList
             ) {
            Provider provider = new Provider(p.getCode(),p.getName());
            providers.add(provider);
        }

            FileService.writeFile( providers);
        responseData.setMessage(" Writing file is success");
        responseData.setStatus("Success");
        responseData.setCode("200");
        return responseData;
    }
}
