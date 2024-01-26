package com.jar.kiranastore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jar.kiranastore.constant.Constant;
import com.jar.kiranastore.entity.Transaction;
import com.jar.kiranastore.service.TransactionService;

@RestController
@RequestMapping(Constant.API_BASE_URL)
public class TransactionController {
 @Autowired
 private TransactionService transactionService;

 @GetMapping
 public List<Transaction> getAllTransactions() {
     return transactionService.getAllTransactions();
 }

 @PostMapping
 public ResponseEntity<Map<String, Object>> addTransaction(@RequestBody Transaction transaction) {
	 Map<String, Object> map=new HashMap<>();
	 try {
		 map.put("message", "Transaction save successfully");
		 map.put("dat", transactionService.addTransaction(transaction));
	} catch (Exception e) {
		map.put("message", "Something wrong");
		return new ResponseEntity(HttpStatus.BAD_REQUEST).ok(map);
	}
     return new ResponseEntity(HttpStatus.OK).ok(map);
 }
 
 @GetMapping("/{startDate}")
 public ResponseEntity<?> getTransactionsByDays(@PathVariable("startDate") String date) {
      return new ResponseEntity(HttpStatus.OK).ok(transactionService.getTransactionsByDays(date));
 }
}


