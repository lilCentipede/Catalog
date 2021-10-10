package vRealizeServiceBroker.catalog.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vRealizeServiceBroker.catalog.model.ItemFull;
import vRealizeServiceBroker.catalog.service.CatalogAPIService;

import java.util.Iterator;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/catalog")
public class CatalogController {


    @Autowired
    private CatalogAPIService catalogAPIService;

    @GetMapping
    public Iterator<JsonNode> getBearerToken(){
        return catalogAPIService.getCatalogWithBearerToken();
    }

    @GetMapping("/items")
    public Iterable<ItemFull> seeCatalog() {
        return catalogAPIService.getCatalogOutOfIterator().findAll();
    }

    @GetMapping("/items/{id}")
    public JsonNode seeItemByID(@PathVariable String id){
        return catalogAPIService.getItemByID(id);
    }

    @PostMapping("/deployment")
    public JsonNode receiveDeployment(@RequestBody JsonNode body){
       return catalogAPIService.postDeployment(body.get("id").asText(),body.get("body"));

    }








}


