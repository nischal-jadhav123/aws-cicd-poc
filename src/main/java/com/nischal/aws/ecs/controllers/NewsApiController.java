package com.nischal.aws.ecs.controllers;

import com.nischal.aws.ecs.dtos.request.GetNewsRequest;
import com.nischal.aws.ecs.exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news/v1")
public class NewsApiController {

    @PostMapping("/getnews")
    public ResponseEntity<?> getNews(@RequestBody GetNewsRequest getNewsRequest) throws CustomException {
        if(getNewsRequest.getFilterType().isEmpty()) throw new CustomException(406,"Bad Request!!","Filter Type can not be empty or null");
        if(getNewsRequest.getSearchKey().isEmpty()) throw new CustomException(406,"Bad Request!!","Search Key can not be empty or null");
        return ResponseEntity.ok().body(getNewsRequest);
    }

}
