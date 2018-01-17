package info.mahendrabagul.springboot2.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import info.mahendrabagul.springboot2.exception.MyRetryException;
import info.mahendrabagul.springboot2.service.MyRetryableServiceTemplate;

@Service
public class RetryService {
	@Autowired
	private RetryTemplate retryTemplate;
	@Autowired
	private MyRetryableServiceTemplate myRetryableServiceTemplate;

	public void withTemplate() throws MyRetryException {
		retryTemplate.execute(context -> {
			myRetryableServiceTemplate.connectToFacebook("https://www.facebook.com");
			return null;
		}, context -> {
			myRetryableServiceTemplate.recoverService(new MyRetryException("Failed to connect to Facebook"),
					"https://www.facebook.com");
			return null;
		});
	}
}