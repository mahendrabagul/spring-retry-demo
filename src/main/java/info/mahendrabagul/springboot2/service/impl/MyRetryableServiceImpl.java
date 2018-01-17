package info.mahendrabagul.springboot2.service.impl;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import info.mahendrabagul.springboot2.exception.MyRetryException;
import info.mahendrabagul.springboot2.service.MyRetryableService;

@Component
public class MyRetryableServiceImpl implements MyRetryableService {

	@Override
	@Retryable(include = { MyRetryException.class }, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	public void connectToFacebook(String url) throws MyRetryException {
		System.out.println("Connecting to Facebook...");
		throw new MyRetryException("Failed to connect to Facebook");
	}

	/*
	 * @Recover public void connectToGoogle(MyRetryException e) {
	 * System.out.println("Connecting to Google instead..."); }
	 */
	@Recover
	public void connectionException(Exception e) throws Exception {
		System.out.println("Retry failure");
		throw e;
	}
}
