package net.guides.springboot2.springboot2jpacrudexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars2")
public class Car {


	private long id;
	private String brand;
	private String yearOfProduction;
	private String color;
	
	public Car() {
		
	}
	
	public Car(String brand, String yearOfProduction, String color) {
		this.brand = brand;
		this.yearOfProduction = yearOfProduction;
		this.color = color;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "brand", nullable = false)
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Column(name = "yearOfProduction", nullable = false)
	public String getYearOfProduction() {
		return yearOfProduction;
	}
	public void setYearOfProduction(String yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	
	@Column(name = "color", nullable = false)
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", yearOfProduction=" + yearOfProduction + ", color=" + color
				+ "]";
	}
	
}
