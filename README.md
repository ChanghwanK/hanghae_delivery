# Overview
향해 Spring 심화 주차 정답 코드 입니다. 강의 내용과 구현 부분이 다소 다를 수 있습니다. 하지만 해당 코드에서 다루고 있는 내용은 반드시 한 번쯤은 접한다면 좋은 것 같습니다.

<br>

### 구현 방식

- 기본적으로 DDD 관점에서 구현 했습니다. 따라서 Service에서는 work flow를 컨트롤 하는 역할을 부여 했으며 비즈니스 로직은 domain model이 진행하는 방향으로 진행 했습니다.
- Java는 기본적으로 null이 허용되기 때문에 null에 취약한 언어 입니다. 현재는 여러분의 코드를 여러분이 다 알고 있기 때문에 null 값을 컨트롤 할 수 있지만 대규모 서비스에서는 코드를 모두 파악할 수 없습니다. 따라서 null을 최대한 방어하는 방법으로 코딩을 했습니다.
- 가끔 Stream API를 적용 했습니다. class를 mapping 할 때 코드를 간결하게 짤 수 있기 때문에 좋은 방안이 될 수 있습니다.
- Request Dto뿐만 아니라 Response Dto도 따로 구현 했습니다. Entity를 Return하는 건 여러 요구사항을 만족시키기 힘들기 때문에 Response Model도 따로 구현 했습니다.
- 그리고 Response는 정형화 되는 것이 좋습니다. (약속이기 때문에) Spring의 RestController가 일부 제공하지만 응답 프론트 입맛에 맞게 내려주기 위해선 ResponseEntity를 활용하는 것이 좀 더 유연하게 Response를 내릴 수 있습니다.
- 마지막으로 Exception을 NullPoint or IllegalArgumentException이 아닌 RuntimeException을 상속해서 커스텀 Exception을 사용 했으며 이를 보다 잘 활용하기 위해 ControllerAdvice를 도입하였습니다.
