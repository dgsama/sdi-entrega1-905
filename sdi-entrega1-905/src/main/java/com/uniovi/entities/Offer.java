package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Offer {
	
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	private Date date;
	private double price;
	private Boolean sold;
	private Boolean vip;
	
	
	@ManyToOne
	public User creator;
	@ManyToOne
	public User buyer;
	
	
	public Offer() {
	}


	public Offer(String title, String description, double price) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.vip = false;
		this.sold = false;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Boolean getSold() {
		return sold;
	}


	public void setSold(Boolean sold) {
		this.sold = sold;
	}


	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public User getBuyer() {
		return buyer;
	}


	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}


	public Boolean getVip() {
		return vip;
	}


	public void setVip(Boolean vip) {
		this.vip = vip;
	}
	

}
