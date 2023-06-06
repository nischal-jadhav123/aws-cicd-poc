package com.nischal.aws.ecs.dtos.request;

public class GetNewsRequest {
    private String filterType;
    private Integer filterValue;
    private Integer pageSize;
    private Integer pageNumber;

    private String sortBy;

    private String searchKey;

    public GetNewsRequest() {
    }

    public GetNewsRequest(String filterType, Integer filterValue, Integer pageSize, Integer pageNumber, String sortBy, String searchKey) {
        this.filterType = filterType;
        this.filterValue = filterValue;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.searchKey = searchKey;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public Integer getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(Integer filterValue) {
        this.filterValue = filterValue;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public String toString() {
        return "GetNewsRequest{" +
                "filterType='" + filterType + '\'' +
                ", filterValue=" + filterValue +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", sortBy='" + sortBy + '\'' +
                ", searchKey='" + searchKey + '\'' +
                '}';
    }
}
