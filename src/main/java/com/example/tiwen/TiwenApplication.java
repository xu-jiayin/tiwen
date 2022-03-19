package com.example.tiwen;

import com.example.tiwen.start.startPy;
import com.example.tiwen.utils.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.tiwen.mapper")
@EnableScheduling
public class TiwenApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiwenApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker createIdWorker() {
        return new SnowflakeIdWorker(0, 0);
    }

    @Bean
    public startPy startpy(){
        return new startPy();
    }
}
