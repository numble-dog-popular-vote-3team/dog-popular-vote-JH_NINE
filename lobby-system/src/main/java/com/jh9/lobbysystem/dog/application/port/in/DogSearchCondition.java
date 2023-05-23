package com.jh9.lobbysystem.dog.application.port.in;

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
}
