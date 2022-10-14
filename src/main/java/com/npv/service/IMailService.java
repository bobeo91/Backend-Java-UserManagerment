package com.npv.service;

public interface IMailService {
	void sendRegistrationUserConfirm(String email);

	void sendUpdatePasswordConfirm(int id, String email);
}
