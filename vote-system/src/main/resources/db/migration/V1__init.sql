CREATE TABLE IF NOT EXISTS dog
(
    id                 BIGINT AUTO_INCREMENT,
    name               VARCHAR(30),
    photo_url           VARCHAR(50),
    simple_description VARCHAR(50),
    detail_description VARCHAR(50),
    thumbs             BIGINT,
    created_date       DATETIME,
    last_modified_date DATETIME,

    PRIMARY KEY (id)
) ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO dog(name, photo_url, simple_description, detail_description, thumbs, created_date, last_modified_date)
values ('넘블이', 'https://example.com/numble.jpg', '귀요미 넘블이 예용', '귀요미 넘블이를 소개합니다', 20, now(), now()),
       ('맥스', 'https://example.com/max.jpg', '누구에게나 친절해요', '맥스는 래브라도 리트리버 믹스입니다.', 10, '2023-05-01 10:00:00', '2023-05-01 10:00:00'),
       ('Bella', 'https://example.com/bella.jpg', '활발한 개', '공원 산책하는 것을 정말 좋아해요.', 5, '2023-05-02 11:00:00' , '2023-05-02 11:00:00'),
       ('찰리', 'https://example.com/charlie.jpg', '귀요미', '꼬리가 꼬불꼬불하고 배 쓰다듬는 것을 좋아해요.', 8, '2023-05-03 12:00:00', '2023-05-03 12:00:00'),
       ('루시', 'https://example.com/lucy.jpg', '천재견', '새로운 재주를 빨리 배워요!.', 12, '2023-05-04 13:00:00', '2023-05-04 13:00:00'),
       ('쿠퍼', 'https://example.com/cooper.jpg', '장난꾸러기', '다른 개들과 장난치는걸 좋아해요.', 7, '2023-05-05 14:00:00', '2023-05-05 14:00:00'),
       ('Luna', 'https://example.com/luna.jpg', '활기찬 개', '항상 가족들에게 에너지를 주는 에너자이저!', 9, '2023-05-06 15:00:00', '2023-05-06 15:00:00'),
       ('막시무스', 'https://example.com/maximus.jpg', '멋진 슈퍼독', '막시무스는 근육질의 멋진 개입니다.', 11, '2023-05-07 16:00:00', '2023-05-07 16:00:00'),
       ('새디', 'https://example.com/sadie.jpg', '젠틀독', '새디는 온화하고 사랑스러운 동반자입니다.', 6, '2023-05-08 17:00:00', '2023-05-08 17:00:00'),
       ('베일리', 'https://example.com/bailey.jpg', '보디가드', '베일리는 가족을 정말 아낀답니다.', 10, '2023-05-09 18:00:00', '2023-05-09 18:00:00'),
       ('데이지', 'https://example.com/daisy.jpg', '햇삐', '데이지는 항상 웃는 얼굴입니다.', 8, '2023-05-10 19:00:00' , '2023-05-10 19:00:00'),
       ('Rocky', 'https://example.com/rocky.jpg', '탐험가', '산과 들판을 좋아해요', 7, '2023-05-11 20:00:00', '2023-05-11 20:00:00'),
       ('Milo', 'https://example.com/milo.jpg', '호기심 대마왕', '호기심 대마왕이입니다!', 9, '2023-05-12 20:00:00', '2023-05-12 20:00:00');
