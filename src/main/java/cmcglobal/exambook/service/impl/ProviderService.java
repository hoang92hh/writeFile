package cmcglobal.exambook.service.impl;

import cmcglobal.exambook.common.ResponseData;
import cmcglobal.exambook.entity.Book;
import cmcglobal.exambook.entity.Provider;
import cmcglobal.exambook.exception.ExceptionHandle;
import cmcglobal.exambook.exception.ExceptionResponse;
import cmcglobal.exambook.exception.ExceptionGetData;
import cmcglobal.exambook.model.response.Iprovider;
import cmcglobal.exambook.model.response.ProviderResponse;
import cmcglobal.exambook.repository.IBookRepository;
import cmcglobal.exambook.repository.IProviderRepository;
import cmcglobal.exambook.repository.impl.IProviderRepositoryExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService implements IProviderService {

    @Autowired

    IProviderRepository providerRepository;

    @Autowired
    IProviderRepositoryExtend providerRepositoryExtend;

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
    public ResponseData getBookOfProvider(String name) {
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
    public ResponseData getAllProivderByConditions(Provider inputElement) {
        ResponseData responseData = new ResponseData();
        List<?> providerList = providerRepository.getAllProviderByConditions(inputElement.getCode(), inputElement.getName());
        responseData.setData(providerList);
        responseData.setMessage("FindAllByConditions");
        responseData.setStatus("Success");
        responseData.setCode("200");
        return responseData;
    }

    @Override
    public ResponseData saveAll(Provider[] providers) {
        ResponseData responseData = new ResponseData();
        try {
            ExceptionGetData.checkDuplicateProvider(providers);
            if(checkDuplicateData( providers)){
                List<Provider> providerList = new ArrayList<>(Arrays.asList(providers));
                providerRepository.saveAll(providerList);
                responseData.setData(providerList);
                responseData.setMessage("Save All");
                responseData.setStatus("Success");
                responseData.setCode("200");
            }else{
                responseData.setMessage("The Array has the object which is exist in Database");
                responseData.setStatus("Fail");
                responseData.setCode("100");
            }


        }
        catch (ExceptionHandle e) {

            responseData.setMessage(e.getMessage());
            responseData.setStatus("ERROR");
            responseData.setCode("400");
        }
        catch (Exception e) {
            responseData.setMessage(e.getMessage());

        }

        return responseData;
    }


    public ResponseData saveAllByHibernate(Provider[] providers) {
        ResponseData responseData = new ResponseData();
        try {
            ExceptionGetData.checkDuplicateProvider(providers);
            if(checkDuplicateData( providers)){
                providerRepositoryExtend.saveAllProviderByHibernate(providers);

                responseData.setData(providers);
                responseData.setMessage("Save All");
                responseData.setStatus("Success");
                responseData.setCode("200");
            }else{
                responseData.setMessage("The Array has the object which is exist in Database");
                responseData.setStatus("Fail");
                responseData.setCode("100");
            }


        }
        catch (ExceptionHandle e) {

            responseData.setMessage(e.getMessage());
            responseData.setStatus("ERROR");
            responseData.setCode("400");
        }
        catch (Exception e) {
            responseData.setMessage(e.getMessage());

        }

        return responseData;
    }

    @Override
    public ResponseData getAllMultiCode(String[] codes) {
        ResponseData responseData = new ResponseData();
        String stringQuery=setQueryStatement(codes);

        try {

//           get All by hibernate repository extends
            List<Provider> providerList =   providerRepositoryExtend.findProviderByCodesList(stringQuery);

            responseData.setData(providerList);
            responseData.setMessage("FindAllByMultiCode");
            responseData.setStatus("Success");
            responseData.setCode("200");
        }
        catch (Exception e){
            responseData.setMessage(e.getMessage());
        }


        return responseData;
    }



    private String setQueryStatement(String[] codes){
        String stringQuery= "Select P.code FROM  Provider P WHERE ";
        int length = codes.length;
        String stringAppend = "";
        for (int i = 0; i <length-1; i++) {
            stringAppend += " P.code ='";

            stringAppend += codes[i];
            stringAppend += "' OR ";

        }
        stringAppend += "P.code = '";
        stringAppend += codes[length-1];
        stringAppend += "' ";


        stringQuery += stringAppend;
        System.out.println(stringQuery);

        return stringQuery;
    }

    private boolean checkDuplicateData(Provider[] providers){
        boolean check = true;
        for(Provider provider : providers){
            Provider provider1 = providerRepository.getProviderByCode(provider.getCode());
            if(provider1!=null){
                check=false;
            }
        }
        return check;
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
