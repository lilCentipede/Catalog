package vRealizeServiceBroker.catalog.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vRealizeServiceBroker.catalog.model.BearerToken;
import vRealizeServiceBroker.catalog.model.Item;
import vRealizeServiceBroker.catalog.model.RefreshToken;
import vRealizeServiceBroker.catalog.repository.CatalogRepository;

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

    public Item getItemByID(String ID) {
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

    public Iterator<JsonNode> getItemsWithBearerToken(){

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

    public CatalogRepository getCatalogOutOfIterator(){
        Iterator<JsonNode> nodeIt = getItemsWithBearerToken();
        while(nodeIt.hasNext()){
            JsonNode node = nodeIt.next();
            Item item = new Item()
                    .setId(node.get("id").asText())
                    .setName(node.get("name").asText())
                    .setDescription(node.get("description").asText());
            catalogRepository.save(item);
        }
        return catalogRepository;
    }

}