package za.co.momentum.active.shoppe.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.momentum.active.shoppe.model.ActiveShoppeProductResponse;


import javax.xml.datatype.DatatypeConfigurationException;

import static org.junit.Assert.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class ActiveShoppeControllerTest {

    //LAUNCHES  THE  ENTIRE APPLICATION

    @Autowired
    TestRestTemplate restTemplate;



    @Test
    public void getAllProductsListTest() throws JSONException, DatatypeConfigurationException, JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(headers);

        //trying

        ResponseEntity<ActiveShoppeProductResponse> response =  restTemplate.exchange(
                ("http://localhost:8089/momentum-active-shoppe/getAllProducts"),
                HttpMethod.GET, requestEntity, ActiveShoppeProductResponse.class);

        assertEquals(3,response.getBody().getProductList().size());
    }

    @Test
    public void purchaseProduct() throws JSONException, DatatypeConfigurationException, JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        //trying

        ResponseEntity<String> response =  restTemplate.exchange(
                ("http://localhost:8089/momentum-active-shoppe/purchaseProduct/2/2"),
                HttpMethod.POST, requestEntity, String.class);

        assertEquals("OK!",response.getBody().toString());
    }


}
