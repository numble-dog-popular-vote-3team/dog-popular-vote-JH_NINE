# Redis와 Kafka, Spring을 활용한 강아지 인기투표 아키텍쳐 만들기

## 1. 요구사항

### 아키텍처
![image](https://github.com/numble-dog-popular-vote-3team/dog-popular-vote-JH_NINE/assets/94362613/65861a68-8f83-4b84-9358-ae04b1ff97a9)

### 요구사항
1. 반려견 목록 검색
- [ ] 기본적으로 목록을 가져온다
  - [ ] 한 페이지에 반려견은 8개를 조회해온다.
  - [ ] 내가 누굴 투표했는지 확인이 가능해야 한다.

- [ ] 필터로 검색할 수 있어야 한다.
  - [ ] 내가 투표한 강아지 
  - [ ] 반려견 이름
  - [ ] 투표 수가 N 이상

- [ ] 정렬도 가능해야 한다.
  - [ ] 이름 순
  - [ ] 투표 랭킹 순
  - [ ] 오름차순 내림차순 모두 가능해야 한다.

2. 투표하기
- [ ] 사용자 회원 가입을 구현 없이 중복 투표를 방지를 조치하라.


### 추가사항
#### Kafka를 사용해보아요
Spring 에서 카프카 Consumer를 구현하여 3번에서 Produce한 이벤트를 전달 받습니다.
이벤트 내용을 통해 데이터베이스에 강아지의 투표 값을 업데이트 합니다.
데이터베이스에 업데이트가 되면 이어서 레디스에도 업데이트 된 강아지의 정보만 새로운 득표 수와 함께 업데이트 합니다. (이후, 1번, 2번에서 레디스를 통해 새로운 정보를 조회할 수 있도록)

#### 이것도 하면 좋아요.
레디스에 저장 시, Spring Cache를 사용하여 어노테이션을 통해 메소드 단위로 구현해도 되고 직접 RedisTemplate 등을 활용하여 serialization 된 데이터를 저장해도 무방합니다. (점수 차이 없음)
프론트엔드 구현의 경우 평가에 반영되지 않습니다.
필수 구현 내용은 아니지만 코드 디자인을 CQRS 패턴을 적용해서 구현하면 코드 설계 부분에 가산점이 있습니다. 다만, 큰 가산점은 아니기 때문에 적용이 어려우실 경우 설계 및 구현과 클린 코드에 집중하시는 것을 권장합니다.

<br>

## 2. 결과
<div align=center><h1>📚 STACKS</h1></div>

<div align=center> 
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
  <img src="https://img.shields.io/badge/apachekafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white"> 
  <br>
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/mongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white">
  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/apachejmeter-D22128?style=for-the-badge&logo=apachejmeter&logoColor=white">
</div> 
  
  
### 전체 아키텍처
![image](https://github.com/numble-dog-popular-vote-3team/dog-popular-vote-JH_NINE/assets/94362613/92a799a1-f067-4d43-b202-2025790f1584)
* 자세한 정보는 [다음](https://jahyun9.notion.site/to-CQRS-WebFlux-b9bc251c63cf45bda17dad0d79272e07)을 참고하세요

<br>

### 모듈 별 아키텍처
![image](https://github.com/numble-dog-popular-vote-3team/dog-popular-vote-JH_NINE/assets/94362613/e045ef02-95c3-47ba-b8e4-f27a38281fa5)
* 계층형 레이어에서 헥사고날 아키텍처로 마이그레이션
* 자세한 정보는 [다음](https://jahyun9.notion.site/to-a3830cbcd64b46d793586037aedcdb12)을 참고하세요!

<br>

### 기술블로그
https://jahyun9.notion.site/Numble-K-2d2352afdfb34eeea3087c455adb9945
