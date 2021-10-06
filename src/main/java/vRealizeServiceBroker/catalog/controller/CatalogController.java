package vRealizeServiceBroker.catalog.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vRealizeServiceBroker.catalog.model.BearerToken;
import vRealizeServiceBroker.catalog.model.Catalog;
import vRealizeServiceBroker.catalog.model.Item;
import vRealizeServiceBroker.catalog.service.CatalogAPIService;
import vRealizeServiceBroker.catalog.service.CatalogService;

import java.util.Iterator;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogAPIService catalogAPIService;

    @GetMapping
    public Iterable<Item> getAllItems(){
        return catalogService.findAll();
    }

    @GetMapping("/items")
    public Catalog seeCatalog() {
        return catalogAPIService.getCatalogOutOfIterator();
    }


}


