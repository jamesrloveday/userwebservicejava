/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.web.objects;

/**
 *
 * @author james
 */
public final class Address {

	public final String firstLine;
	public final String secondLine;
	public final String thirdLine;
	public final String area;
	public final String region;
	public final String county;
	public final String country;
	public final String postCode;

	public Address(String firstLine, String secondLine, String thirdLine, String area, String region, String county, String country, String postCode) {
		this.firstLine = firstLine;
		this.secondLine = secondLine;
		this.thirdLine = thirdLine;
		this.area = area;
		this.region = region;
		this.county = county;
		this.country = country;
		this.postCode = postCode;
	}

}
