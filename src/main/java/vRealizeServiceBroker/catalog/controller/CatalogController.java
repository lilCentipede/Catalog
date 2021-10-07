package vRealizeServiceBroker.catalog.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vRealizeServiceBroker.catalog.model.Item;
import vRealizeServiceBroker.catalog.service.CatalogAPIService;

import java.util.Iterator;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogAPIService catalogAPIService;

    @GetMapping
    public Iterator<JsonNode> getBearerToken(){
        return catalogAPIService.getItemsWithBearerToken();
    }

    @GetMapping("/items")
    public Iterable<Item> seeCatalog() {
        return catalogAPIService.getCatalogOutOfIterator().findAll();
    }

    @GetMapping("/items/{id}")
    public Item seeItemByID(@PathVariable String id){
        return catalogAPIService.getItemByID(id);
    }


}


