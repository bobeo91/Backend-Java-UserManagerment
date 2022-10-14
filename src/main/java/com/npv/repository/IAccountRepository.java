package com.npv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.npv.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account>{
	@Modifying
	@Transactional
	@Query("DELETE Account ac WHERE ac.id IN(:Ids)")
	void deleteMultilAccount(List<Integer> Ids);
	
//	@Query("SELECT ac FROM Account ac WHERE ac.department IS NULL")
//	List<Account> getListAccountsAddDepartment();
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

	Account findByUsername(String username);
	
	//Account getAccountByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("UPDATE Account ac SET ac.password = :newPaswword, ac.status = 1 WHERE ac.id = :id")
	void changePasswordAccount(@Param("id") int id, @Param("newPaswword") String newPaswword);
	
	@Query("SELECT ac FROM Account ac WHERE ac.email =:email")
	Account findAccountByEmail(String email);
}
