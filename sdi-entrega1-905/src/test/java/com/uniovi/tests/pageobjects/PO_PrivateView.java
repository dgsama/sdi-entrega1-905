package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PrivateView extends PO_NavView {

	static public void search(WebDriver driver, String search) {
		WebElement input = driver.findElement(By.name("searchText"));
		input.click();
		input.clear();
		input.sendKeys(search);

		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}