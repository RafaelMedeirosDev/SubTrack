package com.example.SubTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SubTrackApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(e ->
                System.setProperty(e.getKey(), e.getValue())
        );
		SpringApplication.run(SubTrackApplication.class, args);
	}

}
