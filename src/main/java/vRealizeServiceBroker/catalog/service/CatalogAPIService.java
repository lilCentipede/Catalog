package vRealizeServiceBroker.catalog.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import vRealizeServiceBroker.catalog.model.BearerToken;
import vRealizeServiceBroker.catalog.model.Catalog;
import vRealizeServiceBroker.catalog.model.Item;
import vRealizeServiceBroker.catalog.model.RefreshToken;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class CatalogAPIService {
    private final CatalogService catalogService;
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String accessToken;

    @Autowired
    public CatalogAPIService(CatalogService catalogService,
                             RestTemplate restTemplate,
                             @Value("${vRealise.service.broker.api.url}") String apiUrl,
                             @Value("${access.token}") String accessToken) {
        this.catalogService = catalogService;
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.accessToken = accessToken;
    }

    public BearerToken getBearerToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RefreshToken refreshToken = new RefreshToken().setRefreshToken(accessToken);
        HttpEntity<RefreshToken> entity = new HttpEntity<>(refreshToken, headers);
        return restTemplate.exchange(
                apiUrl + "/iaas/api/login",
                HttpMethod.POST,
                entity,
                BearerToken.class).getBody();
    }

    public String getTokenFromBearer(){
        BearerToken bearerToken = getBearerToken();
        return bearerToken.getToken();
    }

    public JsonNode getItemsWithBearerToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getTokenFromBearer());
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<JsonNode> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                apiUrl + "/catalog/api/items",
                HttpMethod.GET,
                entity,
                JsonNode.class).getBody();
    }

}