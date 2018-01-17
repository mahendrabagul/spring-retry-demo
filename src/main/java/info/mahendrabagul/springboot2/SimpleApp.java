package info.mahendrabagul.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableRetry
public class SimpleApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SimpleApp.class, args);
		Foo bean = context.getBean(Foo.class);
		try {
			bean.out("foo");
			System.out.println("Recovered");
		} catch (Exception e) {
			System.out.println("Not recovered: " + e);
		}
		try {
			bean.out("bar");
		} catch (Exception e) {
			System.out.println("Not recovered: " + e);
		}
	}

	@Component
	public static class Foo {

		@Retryable(include = NullPointerException.class, backoff = @Backoff(delay = 100, maxDelay = 101), maxAttempts = 5)
		public void out(String foo) {
			System.out.println(foo);
			if (foo.equals("foo")) {
				throw new NullPointerException();
			} else {
				throw new IllegalStateException();
			}
		}

		@Recover
		public void connectionException(NullPointerException e) {
			System.out.println("Retry failure");
		}

		@Recover
		public void connectionException(Exception e) throws Exception {
			System.out.println("Retry failure");
			throw e;
		}

	}

}
