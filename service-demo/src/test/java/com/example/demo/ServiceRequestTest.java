package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import java.util.*;

import com.example.demo.entity.*;
import com.example.demo.entity.ServiceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RechargeRepository;
import com.example.demo.repository.ServiceRequestRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.RechargeService;
import com.example.demo.service.ServiceRequestServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceRequestTest {

	@Mock
	private ServiceRequestRepository requestRepository;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private RechargeRepository rechargeRepository;

	@InjectMocks 
	private ServiceRequestServiceImpl requestService;
	@InjectMocks
	private AccountService accountService;
	@InjectMocks
	private RechargeService rechargeService;

	@DisplayName("Sample test")
    @Test
    void sampleTest() {
        assertTrue(true);
    }
	

     @Test
	void testCreateRequest() {
		Account account=new Account();
		ServiceRequest request=new ServiceRequest();
		ServiceRequest request1=new ServiceRequest();
		Pack pack=new Pack();
		Recharge recharge=new Recharge();

		List<ServiceRequest> list=new ArrayList<>();
		//list.add(request1);
		
		account.setId(12L);
		account.setRecharge(Arrays.asList(recharge));
		account.setRegisterd(LocalDate.of(2020, 01, 20));
		account.setRequest(list);
		account.setUser(null);
		account.setCurrentPack(pack);
		
		recharge.setAccount(account);
		recharge.setActive(true);
		recharge.setAmount(250.0);
		recharge.setChannels(Arrays.asList("DD","Star"));
		recharge.setDaysValidity(30);
		recharge.setId(1L);
		recharge.setPlanDescription("String");
		recharge.setPurchasedDate(LocalDate.of(2020, 11, 12));
		
		pack.setId(1L);
		pack.setCost(250.0);
		pack.setDaysValidity(30);
		
		
		
		request1.setId(2L);
		request1.setMessage("ServiceOpened");
		request1.setAccount(account);
		request1.setRequestdate(LocalDate.of(2020, 02, 25));
		request1.setStatusOpened(false);
		
		request.setId(1L);
		request.setMessage("ServiceOpened");
		request.setAccount(account);
		request.setRequestdate(LocalDate.of(2020, 02, 25));
		request.setStatusOpened(true);
		
		//LocalDate purchaseDate=null;
		given(rechargeRepository.PurchesedDate(account.getId())).willReturn(recharge.getPurchasedDate());
		given(accountRepository.findById(account.getId())).willReturn(Optional.of(account));

		//doReturn(Optional.of(account)).when(accountRepository.findById(account.getId()));Assertions.assertThat(requestObj).isNotNull();
		//Assertions.assertThat(request.isStatusOpened()).isEqualTo(true);
		
		//when(requestRepository.save(request)).thenReturn(request);   
		ServiceRequest requestObj=requestService.create(request.getId(), account.getId());
		Assertions.assertThat(requestObj).isNotNull();
		// verify(requestRepository).save(any(ServiceRequest.class));
		
		Assertions.assertThat(list.size()).isEqualTo(1);
	}
	@Test
	void shouldUpdateStatus() {
		Account account=new Account();
		ServiceRequest request=new ServiceRequest();
		ServiceRequest request1=new ServiceRequest();
		Pack pack=new Pack();
		Recharge recharge=new Recharge();

		List<ServiceRequest> list=new ArrayList<>();
		//list.add(request1);
		
		account.setId(12L);
		account.setRecharge(Arrays.asList(recharge));
		account.setRegisterd(LocalDate.of(2020, 01, 20));
		account.setRequest(list);
		account.setUser(null);
		account.setCurrentPack(pack);
		
		recharge.setAccount(account);
		recharge.setActive(true);
		recharge.setAmount(250.0);
		recharge.setChannels(Arrays.asList("DD","Star"));
		recharge.setDaysValidity(30);
		recharge.setId(1L);
		recharge.setPlanDescription("String");
		recharge.setPurchasedDate(LocalDate.of(2020, 11, 12));
		
		pack.setId(1L);
		pack.setCost(250.0);
		pack.setDaysValidity(30);
		
		
		
		request1.setId(2L);
		request1.setMessage("ServiceOpened");
		request1.setAccount(account);
		request1.setRequestdate(LocalDate.of(2020, 02, 25));
		request1.setStatusOpened(false);
		
		request.setId(1L);
		request.setMessage("ServiceOpened");
		request.setAccount(account);
		request.setRequestdate(LocalDate.of(2020, 02, 25));
		request.setStatusOpened(true);
		LocalDate date=recharge.getPurchasedDate();
	    
	     
	      given(requestRepository.findById(request.getId())).willReturn(Optional.of(request));
	      given(rechargeRepository.PurchesedDate(account.getId())).willReturn(recharge.getPurchasedDate());
	      //requestRepository.closeRequest(date);
	      ServiceRequest expectedRequest = requestService.update(request.getId());
	      verify(requestRepository).closeRequest(date);  
       	    
	}
	
	@Test
    public void testFindAllOpenRequest() {
        // given
		    Account account=new Account();
			ServiceRequest request=new ServiceRequest();
			List<ServiceRequest> list=new ArrayList<>();
			
			account.setId(1L);
			account.setRecharge(null);
			account.setRegisterd(LocalDate.of(2020, 01, 20));
			account.setRequest(list);
			
			request.setId(1L);
			request.setMessage("ServiceOpened");
			request.setAccount(account);
			request.setRequestdate(LocalDate.of(2020, 02, 25));
			request.setStatusOpened(true);
			  
		    
        List<ServiceRequest> expectedRequest = Arrays.asList(request);

        //Mockito.doReturn(expectedProducts).when(productRepository).findAll();
        given(requestRepository.findAll()).willReturn(expectedRequest);
        
        // when
        List<ServiceRequest> actualProducts = requestService.getAllOpenServiceRequest();
        Assertions.assertThat(actualProducts).isEqualTo(expectedRequest);
        //assertEquals(actualProducts,expectedProducts);
    }
	

	@Test
	public void shouldBeDeleted() {
		Account account=new Account();
		ServiceRequest request=new ServiceRequest();
		List<ServiceRequest> list=new ArrayList<>();
		
		account.setId(1L);
		account.setRecharge(null);
		account.setRegisterd(LocalDate.of(2020, 01, 20));
		account.setRequest(list);
		
		request.setId(12L);
		request.setMessage("ServiceOpened");
		request.setAccount(account);
		request.setRequestdate(LocalDate.of(2020, 02, 25));
		request.setStatusOpened(true);
		
		given(requestRepository.findById(request.getId())).willReturn(Optional.of(request));
		ServiceRequest expectedRequest = requestService.deleteRequest(request.getId());
		Assertions.assertThat(expectedRequest).isNotNull();
		verify(requestRepository,times(1)).delete(request);
		
	}
	

		@Test
		public void testFindRequestByIdWhenExceptionThrown() {
			Account account=new Account();
			ServiceRequest request=new ServiceRequest();
			ServiceRequest request1=new ServiceRequest();
			Pack pack=new Pack();
			Recharge recharge=new Recharge();

			List<ServiceRequest> list=new ArrayList<>();
			//list.add(request1);
			
			account.setId(12L);
			account.setRecharge(Arrays.asList(recharge));
			account.setRegisterd(LocalDate.of(2020, 01, 20));
			account.setRequest(list);
			account.setUser(null);
			account.setCurrentPack(pack);
			
			recharge.setAccount(account);
			recharge.setActive(true);
			recharge.setAmount(250.0);
			recharge.setChannels(Arrays.asList("DD","Star"));
			recharge.setDaysValidity(30);
			recharge.setId(1L);
			recharge.setPlanDescription("String");
			recharge.setPurchasedDate(LocalDate.of(2020, 11, 12));
			
			pack.setId(1L);
			pack.setCost(250.0);
			pack.setDaysValidity(30);
			
			
			
			request1.setId(2L);
			request1.setMessage("ServiceOpened");
			request1.setAccount(account);
			request1.setRequestdate(LocalDate.of(2020, 02, 25));
			request1.setStatusOpened(false);
			
			request.setId(1L);
			request.setMessage("ServiceOpened");
			request.setAccount(account);
			request.setRequestdate(LocalDate.of(2020, 02, 25));
			request.setStatusOpened(true);

			Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
		    	requestService.findServiceRequestById(request.getId());
		    });		
		    String expectedMessage = "Request not found for this id: "+request.getId();
		    String actualMessage = exception.getMessage();
		    assertEquals(actualMessage,expectedMessage);
		}
		
}