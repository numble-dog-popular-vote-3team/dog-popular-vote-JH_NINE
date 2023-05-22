package com.jh9.lobbysystem.dog.application.port.in;

public class DogSearchCondition {

    private String userCookie;
    private int pageSize = 8;
    private Long lastId;
    private String searchKeyword;
    private String sortKeyword;
    private boolean isAscending = false;

    public DogSearchCondition() {
    }

    public void setUserCookie(String userCookie) {
        this.userCookie = userCookie;
    }

    public String getUserCookie() {
        return userCookie;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Long getLastId() {
        return lastId;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public String getSortKeyword() {
        return sortKeyword;
    }

    public boolean isAscending() {
        return isAscending;
    }
}
