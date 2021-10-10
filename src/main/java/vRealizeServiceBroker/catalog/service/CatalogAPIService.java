package vRealizeServiceBroker.catalog.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vRealizeServiceBroker.catalog.model.*;
import vRealizeServiceBroker.catalog.repository.APICatalogRepository;
import vRealizeServiceBroker.catalog.repository.CatalogRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CatalogAPIService {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String accessToken;
    @Autowired
    public CatalogAPIService(RestTemplate restTemplate,
                             @Value("${vRealise.service.broker.api.url}") String apiUrl,
                             @Value("${access.token}") String accessToken) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.accessToken = accessToken;
    }



    private List<String> getTheIDs() {
        List<String> itemIDs = new ArrayList<>();
        Iterator<JsonNode> nodeIt = getCatalogWithBearerToken();
        while(nodeIt.hasNext()) {
            JsonNode node = nodeIt.next();
            itemIDs.add(node.get("id").asText());
        }
        return itemIDs;
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

    public JsonNode getItemByID(String ID) {
        return getItemWithBearerToken(ID);
    }

    public JsonNode makeHeader(String plusUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getTokenFromBearer());
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<JsonNode> entity = new HttpEntity<>(headers);
        return  restTemplate.exchange(
                apiUrl + plusUrl,
                HttpMethod.GET,
                entity,
                JsonNode.class).getBody();

    }
    public Iterator<JsonNode> getCatalogWithBearerToken(){
        JsonNode catalogNode = makeHeader("/catalog/api/items");
        if (catalogNode == null)
            throw new NullPointerException("Null catalog");
       return catalogNode.at("/content").elements();
    }
    public JsonNode getItemWithBearerToken(String withID){
        String path = "/catalog/api/items" +"/" +  withID;
        JsonNode catalogNode = makeHeader(path);
        if (catalogNode == null)
            throw new NullPointerException("Null catalog");
        return catalogNode;
    }

    public CatalogRepository getCatalogOutOfIterator(){
        CatalogRepository catalogRepository = new APICatalogRepository();
        List<String> itemIDS = this.getTheIDs();
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
                            ,node.get("schema").get("required")
                            ))
                    .setIconID(node.get("iconId").asText())
                    .setBulkedRequestLimit(node.get("bulkRequestLimit").asInt());


            catalogRepository.save(item);
        }
        return catalogRepository;
    }




}