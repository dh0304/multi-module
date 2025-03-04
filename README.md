## 프로젝트 의도

기획 변경과 아키텍처 개선을 테스트 코드 기반 리팩터링으로 함께 진행한 [단일 모듈에서 멀티 모듈로의 전환 프로젝트](https://dh0304.github.io/posts/single-to-multi-module-(1)/)

---
## 프로젝트 진행 과정

- [1차 MVP Repository](https://github.com/Cafegory/Cafegory_Backend_REST_API)
- [단일 프로젝트 단일 모듈 Repository](https://github.com/Cafegory2/Cafegory_Backend_REST_API)
- (현재)[멀티 모듈 프로젝트 Repository](https://github.com/dh0304/multi-module)

---
## 애플리케이션의 레이어드 아키텍처
![implement-layer](https://raw.githubusercontent.com/dh0304/ImageRepo/master/uPic/image-20250204202615541.png)
<p align="center"><em>구현 계층이 포함된 레이어드 아키텍처</em> (출처: <a target='_blank' href="https://geminikims.medium.com/%EC%A7%80%EC%86%8D-%EC%84%B1%EC%9E%A5-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4%EB%A5%BC-%EB%A7%8C%EB%93%A4%EC%96%B4%EA%B0%80%EB%8A%94-%EB%B0%A9%EB%B2%95-97844c5dab63">김재민님의 지속 성장 가능한 소프트웨어</a>)</p>

레이어드 아키텍처는 4계층으로 되어있으며 아래와 같은 규칙을 **무조건** 지켜야한다.
> 1. 레이어는 위에서 아래로 순방향으로만 참조 되어야한다.
> 2. 레이어의 참조 방향이 역류 되지 않아야한다.
> 3. 레이어의 참조가 하위 레이어를 건너 뛰지 않아야한다.
> 4. 동일 레이어 간에는 서로 참조하지 않아야한다. (Implemen Layer는 동일 레이어 안에서 서로 참조 가능하다.)

---
## API-Domain-DB 모듈 설계

![Api-Domain-Db 관계 2](https://raw.githubusercontent.com/dh0304/ImageRepo/master/uPic/Api-Domain-Db%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A8%202.svg)

프로젝트는 멀티 모듈로 설계되어 있으며 API-Domain-DB 모듈로 분리되어 있다.

![멀티모듈 api-여러Domain-db-auth](https://raw.githubusercontent.com/dh0304/ImageRepo/master/uPic/%E1%84%86%E1%85%A5%E1%86%AF%E1%84%90%E1%85%B5%E1%84%86%E1%85%A9%E1%84%83%E1%85%B2%E1%86%AF%20api-%E1%84%8B%E1%85%A7%E1%84%85%E1%85%A5Domain-db-auth.svg)

현재 모듈 설계는 위 다이어그램과 동일하다.

---
## Bounded Context 설계

![image-20250212185600950](https://raw.githubusercontent.com/dh0304/ImageRepo/master/uPic/image-20250212185600950.png)

현재 Bounded Context 설계는 위 다이어그램과 동일하다.
