# programmers_2019_summer_internship

설치 및 빌드 방법

1. 리눅스에 docker를 설치한 뒤 postgresql 컨테이너를 설치, 실행한다

> docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=gy -e POSTGRES_DB=springboot --name postgres_boot -d postgres

2. github의 코드를 가져온 뒤에 maven을 이용하여 빌드한다

> git clone https://github.com/aekik1118/programmers_2019_summer_internship.git

> cd programmers_2019_summer_internship

> mvn package

> cd target

> java -jar demo-0.0.1-SNAPSHOT.jar

실제 작동하는 서비스 주소

https://chocod.herokuapp.com/

미리 테스트용 데이터를 넣어 두었습니다.
