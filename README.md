# 환전 프로그램


### 통화 정책에 따라 환전을 해주는 프로그램입니다.

## API 명세서

### 유저 API
| 기능    | Method | URL                 | request  | response | 상태코드                                     |
|-------|--------|---------------------|----------|----------|------------------------------------------|
| 유저 등록 | POST   | /users | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록)  400 비정상 값         |
| 유저 목록 조회 | GET    | /users      | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 조회 실패           |
| 유저 조회 | GET    | /users/{id}      | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 조회 실패   |
| 유저 삭제    | DELETE | /users/{id} | 요청 param     | 정상 처리 메세지        | 200 : OK     (정상 삭제)  400 비정상 값 404 : 없는 값 삭제 시도 |

- 유저 등록

URL : /users

- 성공

요청
```
{
	"name": "Jung",
  "email" : "jgw1202@naver.com"
}
```
응답
```
{
    "id": 1,
    "name": "Jung",
    "email": "jgw1202@naver.com"
}
```

- 실패

- name 값이 없는 경우

요청
```
{
	"name": "",
    "email" : "jgw1202@naver.com"
}
```
응답
```
{
    "errorMessage": "요청값의 형식이 맞지 않습니다.",
    "name": "Name은 필수 입력 값입니다.",
    "errorCode": "ERR001"
}
```

- name 값이 underflow/overflow 난 경우

요청
```
{
	"name": "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
    "email" : "jgw1202@naver.com"
}
```
응답
```
{
    "errorMessage": "요청값의 형식이 맞지 않습니다.",
    "name": "Name은 2자 이상 30자 이하로 입력해주세요.",
    "errorCode": "ERR001"
}
```

- 이메일 형식이 올바르지 않은 경우

요청
```
{
	"name": "Jung",
    "email" : "jgw1202naver.invalid"
}
```
응답
```
{
    "errorMessage": "요청값의 형식이 맞지 않습니다.",
    "errorCode": "ERR001",
    "email": "올바른 이메일 형식을 입력해 주세요"
}
```

- 유저 목록 조회

URL : /users

요청
```
요청 없음
```
응답
```
[
    {
        "id": 1,
        "name": "Jung",
        "email": "jgw1202@naver.com"
    }
]
```


- 유저  조회

URL : /api/users/{id}

요청
```
요청 : PathVarialbe 을 이용해 id 요청
```
응답
```
{
    "id": 1,
    "name": "Jung",
    "email": "jgw1202@naver.com"
}
```

- 유저 삭제

URL : /api/users/{id}

요청
```
요청 : PathVarialbe 을 이용해 id 요청
```
응답
```
 {
    message : "정상적으로 삭제되었습니다."
 }
```
### 통화 API

| 기능    | Method | URL                 | request  | response | 상태코드                                     |
|-------|--------|---------------------|----------|----------|------------------------------------------|
| 통화 등록 | POST   | /currencies | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록)  400 비정상 값         |
| 통화 목록 조회 | GET    | /currencies      | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 조회 실패    |
| 통화화 조회 | GET    | /currencies/{id}     | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 조회 실패   |

- 환율 등록

URL : /currencies

- 성공

요청
```
{
	"currencyName": "USD",
    "exchangeRate": 900,
    "symbol": "$"
}
```
응답
```
{
    "id": 1,
    "currencyName": "USD",
    "exchangeRate": 900,
    "symbol": "$"
}
```

- 실패

- 환율이 올바르지 않은 경우

요청
```
{
	"currencyName": "USD",
    "exchangeRate": -1,
    "symbol": "$"
}
```

응답
```
{
    "exchangeRate": "Exchange Rate은 0보다 커야 합니다.",
    "errorMessage": "요청값의 형식이 맞지 않습니다.",
    "errorCode": "ERR001"
}
```

- 통화 이름이 올바르지 않은 경우

요청
```
{
	"currencyName": "",
    "exchangeRate": 900,
    "symbol": "$"
}
```

응답
```
{
    "currencyName": "Currency Name은 필수 입력 값입니다.",
    "errorMessage": "요청값의 형식이 맞지 않습니다.",
    "errorCode": "ERR001"
}
```

- 통화 목록 조회

URL : /currencies

요청
```
{
  요청 없음
}
```
응답
```
[
    {
        "id": 1,
        "currencyName": "USD",
        "exchangeRate": 900.00,
        "symbol": "$"
    }
]
```

- 통화화 단건 조회

URL : /currencies/{id}

요청
```
{
  요청 없음
}
```
응답
```
{
    "id": 1,
    "currencyName": "currencyName",
    "exchangeRate": 1.00,
    "symbol": "symbol"
}
```


### 환전 요청 API

| 기능    | Method | URL                 | request  | response | 상태코드                                     |
|-------|--------|---------------------|----------|----------|------------------------------------------|
| 환전 요청 | POST   | /exchange-requests | 요청 body  | 등록 정보    | 201 : CREATED (정상 등록)  400 비정상 값  500 네트워크 요청 실패       |
| 환전 요청 조회 | GET    |exchange-requests/user/{id} | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 조회 실패    |
| 상태 수정 | PUT    |exchange-requests/1/cancel     | 요청 param | 목록 조회 정보 | 200 : OK (정상 조회) 400 비정상 값 404 : 수정 실패   |

- 환전 요청

URL : /exchange-requests

- 성공

요청
```
{
  "userId": 1,
  "currencyId": 1,
  "amountBeforeExchange": 10000
}

```
응답
```
{
    "id": 1,
    "userId": 1,
    "currencyId": 1,
    "amountBeforeExchange": 10000,
    "amountAfterExchange": 11.11,
    "status": "normal"
}
```

- 실패

- 유저 아이디가 올바르지 않은 경우

요청
```
{
  "userId": -1,
  "currencyId": 1,
  "amountBeforeExchange": 10000
}

```

응답
```
{
    "errorMessage": "사용자를 찾을 수 없습니다.",
    "errorCode": "ERR002"
}
```

- 환전 전 금액이 null 인 경우

요청
```
{
  "userId": 1,
  "currencyId": 1,
  "amountBeforeExchange": null
}

```

응답
```
{
    "errorMessage": "네트워크 요청에 실패했습니다. 다시 시도해주시기 바랍니다.",
    "errorCode": "ERR500"
}
```

- 환전 요청 정보 조회

URL : /exchange-requests/user/1

요청
```
{
  요청 없음
}
```
응답
```
[
    {
        "id": 1,
        "userId": 1,
        "currencyId": 1,
        "amountBeforeExchange": 10000.00,
        "amountAfterExchange": 11.11,
        "status": "normal"
    }
]
```

- 상태 수정

URL : exchange-requests/1/cancel

요청
```
{
  요청 없음
}
```
응답
```
{
    "id": 1,
    "userId": 1,
    "currencyId": 1,
    "amountBeforeExchange": 10000.00,
    "amountAfterExchange": 11.11,
    "status": "cancelled"
}
```

## ERD

![image](https://github.com/user-attachments/assets/f435b48a-37f0-45ed-aeb5-bdf0ba023956)


### 유저 테이블
| Key      | Logical | physical   | Domain   | Type         | Allow Null |
|----------|---------|------------|----------|--------------|------------|
| PK       | 아이디     | id         |   | BIGINT       | N          |
|     | 이메일     | email      |  | VARCHAR(255)      | N          |
|  | 이름      | name       |  | VARCHAR(255)      | N          |
|    | 생성일자    | created_at |  | TIMESTAMP    | N          |
|    | 수정일자    | updated_at |  | TIMESTAMP    | N          |

### 통화 테이블
| Key      | Logical | physical   | Domain   | Type         | Allow Null |
|----------|---------|------------|----------|--------------|------------|
| PK       | 아이디     | id         |   | BIGINT       | N          |
|     | 환율     | exchange_rate      |  | BIGDECIMAL      | N          |
|  | 통화 이름      | currency_name       |  | VARCHAR(255)      | N          |
|  | 표시      | symbol       |  | VARCHAR(255)      | N          |
|    | 생성일자    | created_at |  | TIMESTAMP    | N          |
|    | 수정일자    | updated_at |  | TIMESTAMP    | N          |

### 통화 테이블
| Key      | Logical | physical   | Domain   | Type         | Allow Null |
|----------|---------|------------|----------|--------------|------------|
| PK       | 아이디     | id         |   | BIGINT       | N          |
| FK    | 유저 아이디     | userId      |  | BIGINT      | N          |
| FK | 통화 아이디      | currencyId      |  | BIGINT     | N          |
|  |   환전 전 금액    | amountBeforeExchange       |  | BIGDECIMAL      | N          |
|  |   환전 후 금액    | amountAfterExchange       |  | BIGDECIMAL      | N          |
|  |  상태  | status       |  | VARCHAR(255)      | N          |
|    | 생성일자    | created_at |  | TIMESTAMP    | N          |
|    | 수정일자    | updated_at |  | TIMESTAMP    | N          |

