package com.jar.kiranastore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jar.kiranastore.constant.Constant;
import com.jar.kiranastore.entity.CalculatedTransaction;
import com.jar.kiranastore.entity.ExchangeRatesResponse;
import com.jar.kiranastore.entity.Transaction;
import com.jar.kiranastore.repository.TransactionRepository;
import com.jar.kiranastore.util.DateFormatter;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private RestTemplate restTemplate;

	public List<Transaction> getAllTransactions() {
		ExchangeRatesResponse response = restTemplate.getForObject(Constant.ItomsRateAPI, ExchangeRatesResponse.class);
		List<Transaction> getAllTransaction = transactionRepository.findAll();
		getAllTransaction.forEach(item -> {
			item.setAmountInUSD(response.getRates().get(item.getItemName()));
			item.setAmountInINR(74.42 * item.getAmountInUSD());
			item.setTotalAmountInUSD(item.getAmountInUSD()*item.getQuantity());
		});
		return transactionRepository.findAll();
	}

	public Transaction addTransaction(Transaction transaction) {
		ExchangeRatesResponse response = restTemplate.getForObject(Constant.ItomsRateAPI, ExchangeRatesResponse.class);
		transaction.setAmountInUSD(response.getRates().get(transaction.getItemName()));
		transaction.setTransactionDate(DateFormatter.currentDateTime());
		transaction.setTotalAmountInUSD(transaction.getAmountInUSD()*transaction.getQuantity());
		 Transaction savetransactiob = transactionRepository.save(transaction);
		 savetransactiob.setAmountInINR(74.42 * savetransactiob.getAmountInUSD());
		 return savetransactiob;
	}

	public CalculatedTransaction getTransactionsByDays(String date) {
		List<Transaction> getAllTransaction = transactionRepository.findByTransactionDate(date);
		CalculatedTransaction transaction=new CalculatedTransaction();
		Map<String,Double> map=new HashMap<>();
		getAllTransaction.forEach(n -> {
			if (map.containsKey(n.getItemName())) {
			map.put(n.getItemName(),n.getTotalAmountInUSD()+map.get((n.getItemName())));
			}else {map.put(n.getItemName(),n.getTotalAmountInUSD());}
		});
		transaction.setDate(date);
		transaction.setItems(map);
		return transaction;
	}
	
}
