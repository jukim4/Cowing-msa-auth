package cowing.auth;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginappApplication {

	public static void main(String[] args) {

		System.out.println("현재 실행 경로 (user.dir): " + System.getProperty("user.dir"));
		// .env 파일 로드
		Dotenv dotenv = Dotenv.configure()
				.directory(System.getProperty("user.dir"))
				.filename(".env")
				.load();

		// 환경변수를 시스템 프로퍼티에 추가
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(LoginappApplication.class, args);
	}

}
