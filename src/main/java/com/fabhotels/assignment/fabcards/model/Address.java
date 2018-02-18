/**
 * 
 */
package com.fabhotels.assignment.fabcards.model;

import javax.persistence.Embeddable;

/**
 * @author prashant
 *
 */
@Embeddable
public class Address {

	private String addressLine;
	private String area;
	private String city;
	private String state;
	private String pincode;

	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
