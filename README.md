# 가계부 프로젝트

![img](https://cdn.inflearn.com/public/courses/336222/cover/e4a928ba-307d-4237-af1d-009f2cff2de1/336222.png?w=300)

> <b>인프런 워밍업 클럽 3기 백엔드 스터디</b> 미션 제출용

### 🔖 미션

| 미션 | 내용                      | 링크                                                                                                    |
|----|-------------------------|-------------------------------------------------------------------------------------------------------|
| 1  | 깃허브 리포지토리에 프로젝트 올리기     | [commit](https://github.com/hj-language/account-book/commit/6660b452caee82cc88287c103c4c950ae581c9d5) |
| 2  | 테이블 설계하기                | [commit](https://github.com/hj-language/account-book/commit/c46749da876abd269312766f8667620c2c360fed) |
| 3  | REST API 설계하기           | [commit](https://github.com/hj-language/account-book/commit/904dc50cc1ba04c16a4c9b59c78b38d193c5f54b) |
| 4  | 조회 REST API 만들기         | [commit](https://github.com/hj-language/account-book/commit/61c30162056019ee2da3971922f65dedf1c08c85) |
| 5  | 삽입, 수정, 삭제 REST API 만들기 | [commit](https://github.com/hj-language/account-book/commit/38372b4343095bd030c61d73c87745f081224f46) |
| 6  | 가상 프로필을 나의 프로필로 바꾸기     | [commit]                                                                                              |
| 7  | 배포한 프로젝트 공유하기           | [link]                                                                                                |

<hr/>

# 프로젝트 소개

## ✏️ 설계 내용

### ERD

<b>v1 - 최초 설계</b>

![스크린샷 2025-03-09 오전 12 30 33](https://github.com/user-attachments/assets/37ef5a15-e8a3-4b4c-a31c-3f69e802949c)

- 유저 별 카테고리를 적용할 수 있도록 유저와 카테고리도 1:N으로 하고 싶었지만, 그러면 거래 쪽에 유저를 두지 않아도 거래를 등록한 사용자가 누군지 알 수 있을 것 같았다.
- 하지만 거래에 유저가 들어가는게 직관적이라고 생각한다. 그래서 거래, 카테고리에 둘 다 유저를 둘지 고민이 되었다.
- 우선 최초 설계는 유저-거래, 카테고리-거래에만 관계를 걸어 두고, 추후 개발하면서 설계가 변경되면 수정하는 과정도 괜찮을 것 같아서 간단하게 ERD를 작성했다.

### API 명세

<b>Preview</b>

!["스크린샷 2025-03-09 오후 1 13 28](https://github.com/user-attachments/assets/1004adb8-0f02-4cea-b9d9-16fd2acf99e4)

- Link: [Postman Link](https://documenter.getpostman.com/view/15047765/2sAYdoFTMq)

# 구현 과정

### 💥 트러블슈팅 기록

- [트러블슈팅 #1 - 레포지터리 등록 안 되는 현상](https://github.com/hj-language/account-book/blob/master/docs/troubleshooting1.md)
- [트러블슈팅 #2 - DDL 실행 에러](https://github.com/hj-language/account-book/blob/master/docs/troubleshooting2.md)

# 구현 내용

### 거래내역 조회: GET /api/v1transactions

**요청**
> GET /api/v1/transactions?categoryId=1&year=2025&month=3&userId=1

**응답**

```json
[
  {
    "id": 1,
    "amount": 1000000,
    "memo": "",
    "transactionDate": "2025-03-20",
    "category": {
      "id": 1,
      "name": "월급",
      "type": "입금"
    }
  }
]
```

### 거래 추가: POST /api/v1/transactions

**요청**

```json
{
  "userId": 1,
  "categoryId": 1,
  "amount": 3760,
  "memo": "아메리카노 4700원에서 카드할인 20%",
  "transactionDate": "2025-03-21"
}
```

**응답**

```json
{
  "id": 14,
  "amount": 3760,
  "memo": "아메리카노 4700원에서 카드할인 20%",
  "transactionDate": "2025-03-09",
  "category": {
    "id": 1,
    "name": "월급",
    "type": "입금"
  }
}
```

### 거래 수정: PUT /api/v1/transactions/{id}

**요청**
> PUT /api/v1/transactions/1

```json
{
  "userId": 1,
  "categoryId": 1,
  "amount": 500000,
  "memo": "월급이 100만원이었는데 삭감됐어요.",
  "transactionDate": "2025-03-21"
}
```

**응답**

```json
{
  "id": 1,
  "amount": 500000,
  "memo": "월급이 100만원이었는데 삭감됐어요.",
  "transactionDate": "2025-03-21",
  "category": {
    "id": 1,
    "name": "월급",
    "type": "입금"
  }
}
```

### 거래 삭제: DELETE /api/v1/transactions/{id}

**요청**
> DELETE /api/v1/transactions/1

```json
{
  "userId": 1
}
```

**응답**

```json
{
  "id": 1,
  "amount": 500000,
  "memo": "월급이 100만원이었는데 삭감됐어요.",
  "transactionDate": "2025-03-21",
  "category": {
    "id": 1,
    "name": "월급",
    "type": "입금"
  }
}
```