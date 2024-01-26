package com.jar.kiranastore.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculatedTransaction {

	private String date;
	private List<Item> item;
	private Map<String, Double> items;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public Map<String, Double> getItems() {
		return items;
	}
	public void setItems(Map<String, Double> items) {
		this.items = items;
	}
}
