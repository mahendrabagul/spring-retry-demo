package info.mahendrabagul.springboot2.service.impl;

import org.springframework.stereotype.Service;

import info.mahendrabagul.springboot2.exception.MyRetryException;
import info.mahendrabagul.springboot2.service.MyRetryableServiceTemplate;

@Service
public class MyRetryableServiceTemplateImpl implements MyRetryableServiceTemplate {
	@Override
	public void connectToFacebook(String url) throws MyRetryException {
		System.out.println("Connecting to Facebook...");
		throw new MyRetryException("Failed to connect to Facebook");
	}

	@Override
	public void recoverService(MyRetryException e, String url) {
		System.out.println("Connecting to Google instead...");
	}
}
