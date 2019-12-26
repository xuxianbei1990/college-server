package college;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication(scanBasePackages={"college"})
@MapperScan(basePackages = "college.dao")
public class CollegeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeServerApplication.class, args);
	}

}
