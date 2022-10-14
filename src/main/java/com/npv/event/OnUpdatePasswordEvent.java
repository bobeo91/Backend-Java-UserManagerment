package com.npv.event;

import org.springframework.context.ApplicationEvent;

import com.npv.entity.Account;

@SuppressWarnings("serial")
public class OnUpdatePasswordEvent extends ApplicationEvent{

	private int id;
	
	private String email;
	
	public OnUpdatePasswordEvent(Object source) {
		super(source);
		Account ac = (Account) source;
		this.id = ac.getId();
		this.email = ac.getEmail();
	}
	
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

}
