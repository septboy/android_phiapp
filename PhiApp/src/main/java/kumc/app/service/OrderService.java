package kumc.app.service;

/**
 * Created by septboy on 2014. 11. 18..
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import kumc.app.container.Params;
import kumc.app.container.OrderContainer;

import static kumc.app.container.OrderContainer.Order;
import static kumc.app.container.OrderContainer.OrderId;

public class OrderService extends AbstractService<Order,OrderId> {

    private static final String BASE_URL = "http://blog/example.com";

    @Override
    public ArrayList<OrderContainer.Order> select(Params request) {
        // The URL for making the GET request
        final String url = BASE_URL + "/users.json";
        //This is where we get the RestTemplate and add the message converters
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        //This is where we call the exchange method and process the response
        OrderContainer orderContainer =  restTemplate.postForObject(url, request ,OrderContainer.class );
        if(null != orderContainer){
            ArrayList<Order> orders = orderContainer.getOrders();
            return orders;
        }else{
            return null;
        }
    }




    @Override
    public Order select(OrderId id) {
        // The URL for making the GET request
        final String url = BASE_URL + "/user?userId={id}";
        //This is where we get the RestTemplate and add the message converters
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

        return restTemplate.getForObject(url, Order.class, id);
    }

    @Override
    public boolean delete(OrderId id) {
        final String url = BASE_URL + "/user?userId={id}";
        //This is where we get the RestTemplate and add the message converters
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

        restTemplate.delete(url, Order.class, id);
        return true;
    }
    /*

        @Override
        public boolean insert(Order order) {

            String url = BASE_URL + "user";
            HttpHeaders requestHeaders = new HttpHeaders();
            // Set the Content-Type header
            requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            //create the request body
            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
            body.add("user[id]", String.valueOf(order.getId()));
            body.add("user[first_name]", order.getFirstName());
            body.add("user[last_name]", order.getLastName());

            //create the request entity
            HttpEntity<?> requestEntity = new HttpEntity<Object>(body, requestHeaders);
            //Get the RestTemplate and add the message converters
            RestTemplate restTemplate = new RestTemplate();

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);
            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
                HttpStatus status = response.getStatusCode();
                if (status == HttpStatus.CREATED) {
                    return true;
                } else {
                    return false;
                }
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                return false;
            }

        }

        @Override
        public Order updateUser(Order updatedOrder) {
            String url = BASE_URL + "updateuser?userid={id}";
            HttpHeaders requestHeaders = new HttpHeaders();
            // Set the Content-Type header
            requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
            body.add("user[id]", String.valueOf(updatedOrder.getId()));
            body.add("user[first_name]", updatedOrder.getFirstName());
            body.add("user[last_name]", updatedOrder.getLastName());

            //create the request entity
            HttpEntity<?> requestEntity = new HttpEntity<Object>(body, requestHeaders);
            RestTemplate restTemplate = new RestTemplate();

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);
            try {
                ResponseEntity<Order> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Order.class, updatedOrder.getId());
                HttpStatus status = response.getStatusCode();
                if (status == HttpStatus.CREATED) {
                    return response.getBody();
                } else {
                    return null;
                }
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                return null;
            }
        }
    */
    private HttpEntity<?> getRequestEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        return new HttpEntity<Object>(requestHeaders);
    }
}