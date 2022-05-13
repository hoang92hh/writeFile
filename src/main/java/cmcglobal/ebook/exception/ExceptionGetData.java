package cmcglobal.ebook.exception;

import cmcglobal.ebook.entity.Provider;
import cmcglobal.ebook.repository.IProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExceptionGetData {


    public static void checkNullException(Object object) throws ExceptionHandle{
        if(object==null){
            throw new ExceptionHandle("Data is not exits", "404");
        }
    }
    public static void checkDuplicateProvider(Provider[] providers) throws ExceptionHandle{

        for(int i=0; i<providers.length; i++){
            for(int j=i+1; j< providers.length;j++){
                if(providers[i].getCode().equals(providers[j].getCode())){
                    throw new ExceptionHandle("The Array has 2 duplicate elepment ","101" );
                }
            }
        }
        System.out.println("ExceptionGetData.checkDuplicateProvider");

    }

}