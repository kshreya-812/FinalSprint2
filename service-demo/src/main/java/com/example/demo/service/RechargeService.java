package com.example.demo.service;

import static com.example.demo.util.AppConstants.OPERATION_FAILED_CONST;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Pack;
import com.example.demo.entity.Recharge;
import com.example.demo.entity.ServiceRequest;
import com.example.demo.exception.OperationFailedException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.PackRepository;
import com.example.demo.repository.RechargeRepository;

@Service
public class RechargeService {
	@Autowired
	private RechargeRepository rechargeRepository;
	@Autowired
	private PackRepository packRepository;
	@Autowired
    private AccountRepository accountRepository;
	
	@Transactional
	public Recharge saveRecharge(Recharge recharge) {
		Recharge rechargeObj=null;
		//Optional<Account> account=accountRepository.findById(recharge.getAccount().getId());
	    
	    //List<Recharge> list=account.get().getRecharge();
		try {
			/*rechargeObj.setId(id);
			rechargeObj.getAmount();
			rechargeObj.getChannels();
			rechargeObj.getDaysValidity();
			rechargeObj.getPlanDescription();
			rechargeObj.getPlanName();
			rechargeObj.getPurchasedDate();
			rechargeObj.getAccount();*/
			rechargeObj =rechargeRepository.save(recharge);
			
			
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}		
		return rechargeObj;
	}
	
	public Recharge updateRechargeDate(Long id) {
		Optional<Recharge> recharge=null;
		Recharge update=null;
		recharge=rechargeRepository.findById(id);
		List<Recharge> list=recharge.get().getAccount().getRecharge();
		if(!recharge.isPresent()) {
			System.out.println("No Recharge");
		}
		else {
			recharge.get().setPurchasedDate(LocalDate.of(2021, 03, 21));
		}try {
			update = rechargeRepository.saveAndFlush(recharge.get());
		}catch(Exception e) {
			throw new OperationFailedException(OPERATION_FAILED_CONST+e.getMessage());
		}
		return update;				
	}	

	public List<Recharge> findRechargesForUserInDescendingOrderByPurchasedDate(Long id){
		Account account=accountRepository.findById(id).get();
		
		List<Recharge> rechargesList = account.getRecharge();
		rechargesList.sort((e1,e2)->e1.getPurchasedDate().compareTo(e2.getPurchasedDate()));
		Collections.reverse(rechargesList);
		return rechargesList;
	}
	
	 public int rechargesForUserCount(Long id) {
		 Optional<Account> account=accountRepository.findById(id);
			List<Recharge> rechargesList = account.get().getRecharge();
			Long count = rechargesList.stream().count();
			
			return (count.intValue()); 
			
	 }
	 
	 public List<Recharge> findAllRechargesInPeriod(LocalDate startDate, LocalDate endDate){
		List<Recharge> recharge= rechargeRepository.findAllRechargesInPeriod(startDate, endDate);
		return recharge;
		 
	 }
	 public int countRechargesInPeriod(LocalDate startDate, LocalDate endDate) {
		 List<Recharge> rechargesList=rechargeRepository.findAllRechargesInPeriod(startDate, endDate);
		 Long count = rechargesList.stream().count();
		 
		return (count.intValue());
		 
	 }
	 /**
	     * calculates revenue by add of all recharges
	     */
	 public double totalRevenueInPeriod(LocalDate startDate, LocalDate endDate) {
		 List<Recharge> totalRevenueInPeriodList=rechargeRepository.findAllRechargesInPeriod(startDate, endDate);
		 double sum = totalRevenueInPeriodList.stream().mapToDouble(x -> x.getAmount()).sum();
			
		return sum;
		 
	 }

	    /**
	     *
	     * recharges done on a pack
	     */
	/* public int rechargesCount(Pack pack) {
		 int count = 0;
			Pack pack1 = packRepository.findById(pack.getId()).get();
			List<Account> accountList = pack1.getAccount();
			for(Account a : accountList) {
				if(a.getCurrentPack().equals(pack)) 
					count = count + 1;
			}
			return count;
	}		
		 */
	 
	    /**
	     * expire recharge if validity is over, mark active flag as false, also remove current plan from account
	     */
     public Recharge expireIfValidityFinished(Account account ,Recharge recharge) {
    	LocalDate Date = LocalDate.now();
 		account.getRecharge().stream().filter(x -> Date.isBefore(x.getPurchasedDate().plusDays(x.getDaysValidity())));
 		List<Recharge> rechargeList = account.getRecharge();
 		for(Recharge r: rechargeList) {
 			if(r.equals(recharge));
 			{
 				r.setActive(false);
 				return r;
 			}	
 		}
 		return null;

     }
     
 	public List<Recharge> getAllRecharge() {
 		List<Recharge> req=rechargeRepository.findAll();

 		return req;
 	}


	}




		