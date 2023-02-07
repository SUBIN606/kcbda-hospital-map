# 나의 반려견은 헌혈을 할 수 있을까?
- 나의 반려견이 헌혈견 조건에 부합하는지 확인할 수 있습니다.
- 한국 헌혈견협회 공식 협력병원을 지도에 나타내고 거리순으로 조회할 수 있습니다.

---

## MongoDB

### docker-compose 실행 예시
초기화 스크립트가 존재하는지 확인한 후, `docker-compose.local.yaml`을 도커 컴포즈로 빌드 후 실행합니다.
``` bash
$ docker-compose -f docker-compose.local.yaml up -d
```
> 도커 네트워크로 연결할 경우 `mongodb-[profile].yml`의 `spring.data.mongodb.host`값을 컨테이너 명으로 변경해야 합니다.

## MariaDB 설정
> 개발환경 에서는 H2 데이터베이스를 사용합니다.

배포 시 `spring.datasource.url`에 호스트명을 컨테이너 명으로 설정해야 합니다.
``` bash
$ docker run -d -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=[root 비밀번호] \
    -e MYSQL_DATABASE=[데이터베이스명] \
    -e MYSQL_USER=[db 유저] \
    -e MYSQL_PASSWORD=[db 유저 비밀번호] \
    -v [로컬 경로]:/var/lib/mysql \
    --network [네트워크명] \
    --name mariadb \
    mariadb
```
