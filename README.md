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


