# Spring-Data-Jpa

## H2 Database
- `chmod 755 h2.sh` 명령어로 실행 권한을 부여한다.
- 최초 한번만 `jdbc:h2:~/datajpa` 경로로 접속한다.
- `~/datajpa.mv.db` 로 datajpa 파일 생성 여부를 확인한다.
- 이후 `jdbc:h2:tcp://localhost/~/datajpa`로 접속하면 된다.