package com.nischal.aws.ecs.service;

import com.nischal.aws.ecs.dtos.request.GetNewsRequest;
import org.springframework.http.ResponseEntity;

public interface GetNewsService {
    public ResponseEntity<?> getNews(GetNewsRequest getNewsRequest);
}
