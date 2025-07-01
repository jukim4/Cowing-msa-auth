FROM openjdk:21-jdk-slim

WORKDIR /app

# Gradle Wrapper 복사
COPY gradlew .
COPY gradle gradle

# 의존성 파일 복사 (캐싱 활용)
COPY build.gradle .
COPY settings.gradle .

# 의존성 다운로드
RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# 소스 코드 복사
COPY src src

# 빌드
RUN ./gradlew build -x test --no-daemon

EXPOSE 8080

# 실행
CMD ["./gradlew", "bootRun", "--no-daemon"]