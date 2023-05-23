package com.jh9.votesystem.dog.application.port.in;

public class DogSearchCondition {

    private String userCookie;
    private int pageSize;
    private long lastId;

    private String name;
    private int thumbs;
    private boolean onlyVote;

    // 정렬 기준
    private String sortKey;
    private boolean doAscending;

    public String getUserCookie() {
        return userCookie;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getLastId() {
        return lastId;
    }

    public String getName() {
        return name;
    }

    public int getThumbs() {
        return thumbs;
    }

    public String getSortKey() {
        return sortKey;
    }

    public boolean isOnlyVote() {
        return onlyVote;
    }

    public boolean doAscending() {
        return doAscending;
    }
}
