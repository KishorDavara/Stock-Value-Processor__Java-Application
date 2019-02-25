package stockValueProcessor;

import java.util.Date;

public class Company {
	private String name;
	private Date earliestDate;
	private Date latestDate;
	private double earliestValue;
	private double latestValue;
	private double largestIncreaseValue;
	
	public Company(String _name) {
		name = _name;
	}
	public String getName() {
		return name;
	}
	public Date getEarliestDate() {
		return earliestDate;
	}
	public void setEarliestDate(Date date) {
		earliestDate = date;
	}
	public Date getLatestDate() {
		return latestDate;
	}
	public void setLatestDate(Date date) {
		latestDate = date;
	}
	public double getEarliestValue() {
		return earliestValue;
	}
	public void setEarliestValue(double stockValue) {
		earliestValue = stockValue;
	}
	public double getLatestValue() {
		return latestValue;
	}
	public void setLatestValue(double stockValue) {
		latestValue = stockValue;
	}
	public double getLargestIncreaseValue() {
		return latestValue - earliestValue;
	}
	
}
