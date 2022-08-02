한국 헌혈견협회 공식 협력병원을 지도에 나타내고 거리순으로 조회할 수 있습니다.

### 최초 화면
- 지도의 마커를 클릭하면 마커의 좌표를 중심으로 zoom-in 됩니다.
- 왼쪽 리스트 항목을 클릭해도 해당 항목의 좌표를 중심으로 zoom-in 됩니다.
- 지구+돋보기 모양 아이콘을 클릭할 경우 네이버 지도 웹 페이지가 새창으로 생성됩니다.

<img width="950" alt="스크린샷 2022-07-05 오후 5 24 03" src="https://user-images.githubusercontent.com/66243183/177284482-3ea093a9-a9c8-4f7f-a383-a98abd8e36a2.png">

## 거리순 보기
- `거리순` 버튼을 클릭하면 기기의 위치 정보를 수집해 위치 기준 가까운 순서대로 동물병원 목록을 정렬합니다.
- 동물병원 명 밑에 현재 위치로부터 떨어진 거리를 출력합니다.
  <img width="961" alt="image" src="https://user-images.githubusercontent.com/66243183/177285399-533a7195-e25b-4e9d-a86b-8671d999588f.png">


## MongoDB설정

### 방법1. docker-compose
초기화 스크립트가 존재하는지 확인한 후, `docker-compose.local.yaml`을 도커 컴포즈로 빌드 후 실행합니다.
``` bash
$ docker-compose -f docker-compose.local.yaml up -d
```

### 방법2. docker run
``` bash
$ docker run -d -p 27017:27017 \
    -e MONGO_INITDB_ROOT_USERNAME=root \
    -e MONGO_INITDB_ROOT_PASSWORD=password \ 
    -e MONGO_INITDB_DATABASE=data \
    -v [초기화 스크립트 경로]:/docker-entrypoint-initdb.d/mongo-init.js:ro \
    -v [로컬 경로]:/[vm 경로] \
    --network [네트워크명] \
    --name mongodb 
    mongo
```
도커 네트워크로 연결할 경우 `mongodb-[profile].yml`의 `spring.data.mongodb.host`값을 컨테이너 명으로 변경해야 합니다.
