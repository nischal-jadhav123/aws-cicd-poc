package com.nischal.aws.ecs.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponseDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("totalResults")
    private int totalResults;

    @JsonProperty("articles")
    private Article[] articles;

    public NewsApiResponseDto() {
    }

    public NewsApiResponseDto(String status, int totalResults, Article[] articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "NewsApiResponseDto{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + Arrays.toString(articles) +
                '}';
    }
}
