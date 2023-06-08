package com.nischal.aws.ecs.controllers;

import com.nischal.aws.ecs.dtos.request.GetNewsRequest;
import com.nischal.aws.ecs.exceptions.CustomException;
import com.nischal.aws.ecs.service.GetNewsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class NewsApiController {

    @Autowired
    private GetNewsService getNewsService;

    @Operation(summary = "This is post api when passed filterType as days, filterValue as 2, searchKey as cricket, will return Last 2 days news of cricket")
    @PostMapping("/news")
    public ResponseEntity<?> getNews(@RequestBody GetNewsRequest getNewsRequest) throws CustomException {
        if(getNewsRequest.getFilterType().isEmpty()) throw new CustomException(406,"Bad Request!!","Filter Type can not be empty or null");
        if(getNewsRequest.getSearchKey().isEmpty()) throw new CustomException(406,"Bad Request!!","Search Key can not be empty or null");
        if(getNewsRequest.getFilterValue() == null){
            getNewsRequest.setFilterType("hours");
            getNewsRequest.setFilterValue(12);
        }
        if(getNewsRequest.getPageSize() == null || getNewsRequest.getPageSize() == 0){
            getNewsRequest.setPageSize(10);
            getNewsRequest.setPageNumber(1);
        }
        return getNewsService.getNews(getNewsRequest);
    }

}
