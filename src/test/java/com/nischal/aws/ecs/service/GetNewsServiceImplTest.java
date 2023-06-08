package com.nischal.aws.ecs.service;
import com.nischal.aws.ecs.dtos.request.GetNewsRequest;
import com.nischal.aws.ecs.dtos.response.NewsApiResponseDto;
import com.nischal.aws.ecs.utils.DateTimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetNewsServiceImplTest {

    private GetNewsServiceImpl getNewsService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        getNewsService = new GetNewsServiceImpl(restTemplate);
    }

    @Test
    public void testCalculateRange() {
        // Set up the test data
        LocalDateTime currentTime = LocalDateTime.of(2023, 6, 8, 12, 0); // Example current time
        int amount = 3;
        String chronoUnit = "days";

        // Perform the calculation
        LocalDateTime result = DateTimeUtil.calculateRange(currentTime, amount, chronoUnit);

        // Define the expected result based on the inputs
        LocalDateTime expected = LocalDateTime.of(2023, 6, 5, 12, 0); // Expected result for the given inputs

        // Assert that the actual result matches the expected result
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetNews_SuccessfulResponse() {
        // Mock the necessary objects and data
        GetNewsRequest request = new GetNewsRequest();
        request.setFilterValue(10);
        request.setFilterType("days");
        request.setSearchKey("cricket");
        request.setSortBy("popularity");
        request.setPageSize(5);
        request.setPageNumber(1);


        NewsApiResponseDto mockResponseDto = new NewsApiResponseDto();
        // Set the properties of the mock response DTO as needed

        ResponseEntity<NewsApiResponseDto> mockResponseEntity =
                new ResponseEntity<>(mockResponseDto, HttpStatus.OK);

        // Mock the behavior of the RestTemplate
        when(restTemplate.exchange(
                org.mockito.ArgumentMatchers.any(RequestEntity.class),
                org.mockito.ArgumentMatchers.eq(NewsApiResponseDto.class)))
                .thenReturn(mockResponseEntity);

        // Invoke the method being tested
        ResponseEntity<?> responseEntity = getNewsService.getNews(request);

        // Assert the expected result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
