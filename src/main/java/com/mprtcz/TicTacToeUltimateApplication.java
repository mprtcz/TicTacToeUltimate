package com.mprtcz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.mprtcz.tictactoeultimate.repository")
@EntityScan("com.mprtcz.tictactoeultimate.model")
public class TicTacToeUltimateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeUltimateApplication.class, args);
	}
}
