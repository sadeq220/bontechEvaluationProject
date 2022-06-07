package ir.sadeqcloud.BontechEvaluationProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement//for declarative transaction management
public class BontechEvaluationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BontechEvaluationProjectApplication.class, args);
	}

}