package com.nischal.aws.ecs.service;

import com.nischal.aws.ecs.dtos.request.GetNewsRequest;
import com.nischal.aws.ecs.dtos.response.NewsApiResponseDto;
import com.nischal.aws.ecs.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GetNewsServiceImpl implements GetNewsService{

    Logger logger = LoggerFactory.getLogger(GetNewsServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    public GetNewsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> getNews(GetNewsRequest getNewsRequest) {
        String baseUrl = "https://newsapi.org/v2/everything";
        LocalDateTime toTime = LocalDateTime.now();
        LocalDateTime fromTime = DateTimeUtil.calculateRange(toTime, getNewsRequest.getFilterValue(), getNewsRequest.getFilterType());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String sortBy = getNewsRequest.getSortBy().isEmpty() ? "publishedAt" : getNewsRequest.getSortBy();
        // Build the query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("q", getNewsRequest.getSearchKey())
                .queryParam("pageSize", getNewsRequest.getPageSize())
                .queryParam("page", getNewsRequest.getPageNumber())
                .queryParam("from", formatter.format(fromTime))
                .queryParam("to", formatter.format(toTime))
                .queryParam("sortBy", sortBy);


        // Set the request URL
        String url = builder.toUriString();
        logger.error(url);
        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", "8cad8b767d6046029b046ca76fd5604f"); // Replace with your actual API key
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a RequestEntity with the URL, headers, and HTTP method
        RequestEntity<Void> requestEntity;
        try {
            requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(url));
        } catch (URISyntaxException e) {
            // Handle URI syntax exception;
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        // Send the HTTP GET request
        ResponseEntity<NewsApiResponseDto> responseEntity;
        try {
            responseEntity = restTemplate.exchange(requestEntity, NewsApiResponseDto.class);
        } catch (HttpClientErrorException ex) {
            // Handle HTTP client error (4xx)
            if (ex.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                logger.error("Error: Too many requests. You may need to upgrade to a paid plan.");
            } else {
                logger.error("Error: " + ex.getStatusCode());
            }
            return ResponseEntity.internalServerError().body(ex.getMessage());
        } catch (HttpServerErrorException ex) {
            // Handle HTTP server error (5xx)
            return ResponseEntity.internalServerError().body(ex.getMessage());
        } catch (Exception ex) {
            // Handle other exceptions
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

        // Check the response status code
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            NewsApiResponseDto newsApiResponseDto = responseEntity.getBody();
            return ResponseEntity.ok().body(newsApiResponseDto);
        } else {
            // Handle the error response
            return ResponseEntity.internalServerError().body("Internal Server Error!! Try later");
        }

    }
}
