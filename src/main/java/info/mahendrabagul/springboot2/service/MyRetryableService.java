package info.mahendrabagul.springboot2.service;

import info.mahendrabagul.springboot2.exception.MyRetryException;

public interface MyRetryableService {
	void connectToFacebook(String url) throws MyRetryException;
	// void recoverService(MyRetryException e, String url);
}