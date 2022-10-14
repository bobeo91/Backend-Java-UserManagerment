package com.npv.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.npv.dto.AccountDTO;
import com.npv.entity.Account;
import com.npv.entity.AccountFilterForm;

public interface IAccountService extends UserDetailsService{
	List<Account> getListAccounts();
	
	//List<Account> getListAccountsAddDepartment();
	
	Page<Account> getPagingAccounts(Pageable pageable, String search, AccountFilterForm acFF);
	
	void updateAccount(AccountDTO acDTO);
	
	void deleteAccount(int id);
	
	void deleteMultipleAccounts(List<Integer> ids);
	
	void addNewAccount(AccountDTO acDTO);
	
	boolean isAccountExists(int id);
	
	public boolean isAccountExistsByUsername(String username);
	
	public Account getAccountByUsername(String username);
	
	public void createAccount(Account ac);

	Account getAccountInfoById(int userId);
	
	Account findAccountByEmail(String email);
	
	void activeAccount(int id);

	void changePasswordAccount(Account ac);
	
}
