# Roxy API Server 
roxy api server 는 roxy-blog project 의 api 서버입니다.  
roxy-blog project 는 [다음](https://github.com/rainbow-flavor/roxy-blog)을 참조하세요.  
호스팅 되는 블로그는 [여기](https://roxy.iro.ooo)에서 확인할 수 있습니다.

## 상태
![build](https://github.com/rainbow-flavor/roxy-api-server/actions/workflows/docker-image.yml/badge.svg)
![Known Vulnerabilities](https://snyk.io/test/github/rainbow-flavor/roxy-api-server/badge.svg)
![hits](https://img.shields.io/endpoint?logo=Fireship&url=https%3A%2F%2Fhits.dwyl.com%2Frainbow-flavor%2Froxy-api-server.json)
## 포함 기능 리스트
### 조회 수 카운팅 기능
방문자에 대해 조회 카운트를 합니다. IP, MAC 에 대한 제한이 없습니다.

### 조회 시각화
클라우드 플레어에서 삽입하는 CF-Connecting-IP header 를 참조하여 시각적으로 접속 국가 및 시간에 대한 통계와 그래프를 생성할 수 있도록 합니다.

### 코드 러너 기능
Web 에서 실행할 수 있는 코드를 올바른 형식으로 요청 시 샌드박스 환경에서 코드를 실행하여 결과값을 응답합니다.
stdout, stderrr, compile error, memmory, time 등을 포함하여 해당 정보를 활용하여 roxy blog 에 포함된 웹 IDE 에 출력합니다.

### 댓글 서비스 기능
글이 위치한 url path 별로 덧글을 저장하며 출력합니다. CRUD 엔드 포인트를 제공하며, Create, Update, Delete 에 요구하는 Password 는 단방향 암호화됩니다.

### 변경점
***Deprecated***
클라우드 플레어에서 삽입하는 CF-Connecting-IP header 를 참조하여 IP 및 시간을 카운팅 합니다.
1시간에 한번 같은 경로의 Path 요청에 대해 조회 수를 카운팅합니다. 

IP 를 확인할 수 없는 경우 공백으로 치환하여 시간에 제한 없이 카운트 합니다.



## 빌드 및 배포
### 도커라이징 
1. 빌드는 Github Self Host Runner 를 사용하여 진행됩니다. 
2. 소스는 Dockerfile 을 기반으로 Docker Image 로 빌드합니다.
3. 빌드된 이미지는 Private docker registry 서버에 업로드 됩니다.

### 배포
1. private docker registry 에 이미지가 업로드되면
2. Bare-metal 로 구축된 Kubernetes Cluster 에서 apply 쉘 스크립트가 실행됩니다.
3. 자동으로 갱신된 이미지를 긁어오며 정의된 deployment 와 service 가 재기동됩니다.


