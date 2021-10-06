package vRealizeServiceBroker.catalog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.support.NullValue;
import vRealizeServiceBroker.catalog.repository.CatalogRepository;
import vRealizeServiceBroker.catalog.repository.InMemoryCatalogRepository;

@SpringBootTest
public class CatalogAPIServiceTests {
    @Autowired
    CatalogAPIService service;
    @Test
    public String checkIfBearerIsNULL(){

        try{
            service.getCatalogOutOfIterator();
        }
        catch(NullPointerException nullvalue){
           return nullvalue.getMessage();
        }
        return "Getting Catalog successfuly";
    }

}
