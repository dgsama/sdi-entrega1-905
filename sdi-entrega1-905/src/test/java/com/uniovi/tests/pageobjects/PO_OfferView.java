package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_OfferView extends PO_NavView {
	static public void fillForm(WebDriver driver, String titulo, String descripcion, String price) {
		WebElement title1 = driver.findElement(By.name("title"));
		title1.click();
		title1.clear();
		title1.sendKeys(titulo);
		WebElement descrip = driver.findElement(By.name("description"));
		descrip.click();
		descrip.clear();
		descrip.sendKeys(descripcion);
		WebElement pric = driver.findElement(By.name("price"));
		pric.click();
		pric.clear();
		pric.sendKeys(price);
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
