package info.mahendrabagul.springboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.mahendrabagul.springboot2.exception.MyRetryException;
import info.mahendrabagul.springboot2.service.MyRetryableService;
import info.mahendrabagul.springboot2.template.RetryService;

@SpringBootApplication
@EnableRetry
@RestController
@RequestMapping("/")
public class ArtajaSpringBoot2Application {

	@Autowired
	private MyRetryableService myRetryableService;

	@Autowired
	private RetryService retryService;

	public static void main(String[] args) {
		SpringApplication.run(ArtajaSpringBoot2Application.class, args);
	}

	@GetMapping
	public String connectToFacebook() throws MyRetryException {
		myRetryableService.connectToFacebook("https://www.facebook.com/");
		return "Hello Mahendra Bagul";
	}

	@GetMapping("/withTemplate")
	public String connectToFacebookWithTemplate() throws MyRetryException {
		retryService.withTemplate();
		return "Hello Mahendra Bagul from retry template";
	}
}
