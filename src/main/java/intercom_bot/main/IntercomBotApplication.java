package intercom_bot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "intercom_bot")
@EnableJpaRepositories(basePackages = "intercom_bot.data.repositories")
@EntityScan("intercom_bot.data.entities")
public class IntercomBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntercomBotApplication.class, args);
    }
}
