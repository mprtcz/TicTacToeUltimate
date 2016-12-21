package com.mprtcz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
		basePackageClasses = { TicTacToeUltimateApplication.class, Jsr310JpaConverters.class }
)
@SpringBootApplication
public class TicTacToeUltimateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeUltimateApplication.class, args);
	}
}
