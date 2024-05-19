## 🌳 폴더 구조

```
src─|
    java
    ├─common : Exception관련 파일이나 공통 클레스(aop, validation, converter, image upload) 파일로 구성
    ├─config : 각종 설정파일 구성
    ├─controller : 컨트롤러 파일 보관
    |    ├─ member (회원 패키지)
    |    ├─ product (상품 패키지)
    |    ├─ planner (플래너 패키지)
    |    ├─ location (지역 정보 패키지)
    ├─domain : mapper의 반환 값으로 사용될 클래스 구성
    |    ├─ member
    |    ├─ product
    |    ├─ planner
    |    ├─ location
    ├─dto
    |    ├─ request (request 즉, 요청 객체로 사용자가 서버쪽으로 데이터를 전달할 때 받을 DTO 선언)
    |        |    ├─ member (회원 패키지)
    |        |    ├─ product (상품 패키지)
    |        |    ├─ planner (플래너 패키지)
    |        |    ├─ location (지역 정보 패키지)
    |    ├─ response (response 즉, 응답 객체로 DB의 데이터를 해당 DTO로 받아서 최종적으로 사용자에게 반환할 DTO 선언)
    |        |    ├─ member (회원 패키지)
    |        |    ├─ product (상품 패키지)
    |        |    ├─ planner (플래너 패키지)
    |        |    ├─ location (지역 정보 패키지)
    ├─mapper : 매퍼 파일 보관
    |    ├─ member
    |    ├─ product
    |    ├─ planner
    |    ├─ location
    ├─security : spring security 파일 구성
    ├─service : 서비스 파일 보관
    |    ├─ member
    |    ├─ product
    |    ├─ planner
    |    ├─ location
    resources
    ├─mapper
    |    ├─ member
    |    ├─ product
    |    ├─ planner
    |    ├─ location
    |
    ├─ static
    │          ├─ css (css 파일 보관)
    │          │    ├─ member
    │          │    ├─ product
    │          │    ├─ planner
    │          │    ├─ location
    │          │
    │          │
    │          ├─ image (image 파일 보관)
    │          │    ├─ member
    │          │    ├─ product
    │          │    ├─ planner
    │          │    ├─ location
    │          │
    │          │
    │          ├─ js (js 파일 보관)
    │               ├─ member
    │               ├─ product
    │               ├─ planner
    │               ├─ location
    |
    |
    ├─ template
    |    ├─ member
    |    ├─ product
    |    ├─ planner
    |    ├─ location
```
