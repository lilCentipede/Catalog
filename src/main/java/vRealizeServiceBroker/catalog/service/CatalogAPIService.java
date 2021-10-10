package vRealizeServiceBroker.catalog.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vRealizeServiceBroker.catalog.model.*;
import vRealizeServiceBroker.catalog.repository.CatalogRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CatalogAPIService {
    private final CatalogRepository catalogRepository;
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String accessToken;


    @Autowired
    public CatalogAPIService(CatalogRepository catalogRepository,
                             RestTemplate restTemplate,
                             @Value("${vRealise.service.broker.api.url}") String apiUrl,
                             @Value("${access.token}") String accessToken) {
        this.catalogRepository = catalogRepository;
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.accessToken = accessToken;
    }

    public ItemFull getItemByID(String ID) {
        return catalogRepository.findByID(ID).get();
    }

    private BearerToken getBearerToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        RefreshToken refreshToken = new RefreshToken().setRefreshToken(accessToken);

        HttpEntity<RefreshToken> entity = new HttpEntity<>(refreshToken, headers);
        BearerToken token =  restTemplate.exchange(
                apiUrl + "/iaas/api/login",
                HttpMethod.POST,
                entity,
                BearerToken.class).getBody();
        if(token == null){
            throw new NullPointerException("Null token");
        }
        return token;
    }

    private String getTokenFromBearer(){
        BearerToken bearerToken = getBearerToken();
        return bearerToken.getToken();
    }

    public Iterator<JsonNode> getCatalogWithBearerToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getTokenFromBearer());
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<JsonNode> entity = new HttpEntity<>(headers);
        JsonNode catalogNode =  restTemplate.exchange(
                apiUrl + "/catalog/api/items",
                HttpMethod.GET,
                entity,
                JsonNode.class).getBody();

        if (catalogNode == null)
            throw new NullPointerException("Null catalog");

       return catalogNode.at("/content").elements();
    }
    public JsonNode getItemWithBearerToken(String withID){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getTokenFromBearer());
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<JsonNode> entity = new HttpEntity<>(headers);
        JsonNode catalogNode =  restTemplate.exchange(
                apiUrl + "/catalog/api/items" + "/" + withID,
                HttpMethod.GET,
                entity,
                JsonNode.class).getBody();

        if (catalogNode == null)
            throw new NullPointerException("Null catalog");
        System.out.println("yey");
        return catalogNode;

    }

    public CatalogRepository getCatalogOutOfIterator(){
        List<String> itemIDS = this.getTheIDs();
        System.out.println(itemIDS);
        for(var itemID : itemIDS) {
            JsonNode node = getItemWithBearerToken(itemID);
            ItemFull item = new ItemFull()
                    .setId(node.get("id").asText())
                    .setName(node.get("name").asText())
                    .setDescription(node.get("description").asText())
                    .setType(new ItemType().setAll(
                            node.get("type").get("id").asText()
                            , node.get("type").get("link").asText()
                            , node.get("type").get("name").asText()
                            ))
                    .setProjectIds(node.get("projectIds"))
                    .setCreatedAt(node.get("createdAt").asText())
                    .setCreatedBy(node.get("createdBy").asText())
                    .setLastUpdatedAt(node.get("lastUpdatedAt").asText())
                    .setGetLastUpdatedBy(node.get("lastUpdatedBy").asText())
                    .setSchema(new ItemSchema().setAll(
                            node.get("schema").get("type").asText()
                            ,node.get("schema").get("encrypted").asBoolean()
                            ,node.get("schema").get("properties")
                            ))
                    .setIconID(node.get("iconId").asText())
                    .setBulkedRequestLimit(node.get("bulkRequestLimit").asInt());


            catalogRepository.save(item);
        }
        return catalogRepository;
    }
    public List<String> getTheIDs() {
        List<String> itemIDs = new ArrayList<>();
        Iterator<JsonNode> nodeIt = getCatalogWithBearerToken();
        while(nodeIt.hasNext()) {
            JsonNode node = nodeIt.next();
            itemIDs.add(node.get("id").asText());
        }
        return itemIDs;
    }

}