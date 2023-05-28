package com.jh9.lobbysystem.dog.application.port.in;

public record DogSearchCondition(
    String userCookie,
    Integer pageSize,
    String lastValue,
    String name,
    Integer thumbs,
    Boolean onlyVote,   // 내가 투표한 목록만 보기
    String sortKey,
    Boolean doAscending
) {

    public DogSearchCondition(String userCookie, Integer pageSize,
        String lastValue, String name, Integer thumbs, Boolean onlyVote, String sortKey,
        Boolean doAscending) {
        this.userCookie = userCookie;
        this.pageSize = (pageSize == null) ? 8 : pageSize;
        this.lastValue = lastValue;
        this.name = name;
        this.thumbs = thumbs;
        this.onlyVote = onlyVote;
        this.sortKey = (sortKey == null) ? "id" : sortKey;
        this.doAscending = doAscending != null && doAscending;
    }
}
