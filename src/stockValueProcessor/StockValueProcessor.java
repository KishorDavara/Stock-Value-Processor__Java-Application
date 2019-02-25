package stockValueProcessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StockValueProcessor {
	
	public static Map<String, Company> companies = new HashMap<String, Company>();
	public static double largestIncreaseValue = 0;
	public static String largestIncreaseCompany = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*String csvFile = "";
		if (args.length < 2) {//if the number of arguments is < 2
			System.out.print("Please include an argument for the csv file");
			return;
		}
		csvFile = args[1];*/
		
		String csvFile = "src/values.csv";
		
		String line = "";
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
			//Skip the title line first
			String titleLine = br.readLine();
			//Parse through each line and get the stock information
			while ((line = br.readLine()) != null) {
				String[] stockInfo = line.split(",");
				
				//Skip the line where the value is "N/A"/"unknown"/"-",etc.
				boolean numeric = true;
				try {
		            Double num = Double.parseDouble(stockInfo[3]);
		        } catch (NumberFormatException e) {
		            numeric = false;
		        }
				//Only parse the line when the value is available
				if (numeric) {
					String companyNameOnThisLine = stockInfo[0];
					Date dateOnThisLine = sd.parse(stockInfo[1]);
					double stockValueOnThisLine = Double.parseDouble(stockInfo[3]);
					
					//If the company is not in the dictionary
					Company company = null;
					if (!companies.containsKey(companyNameOnThisLine)) {
						company = new Company(companyNameOnThisLine);
						company.setEarliestDate(dateOnThisLine);
						company.setLatestDate(dateOnThisLine);
						company.setEarliestValue(stockValueOnThisLine);
						company.setLatestValue(stockValueOnThisLine);
						companies.put(companyNameOnThisLine, company);
					}
					else { //If the company is already in the dictionary
						company = companies.get(companyNameOnThisLine);
						if (dateOnThisLine.before(company.getEarliestDate())){
							company.setEarliestDate(dateOnThisLine);
							company.setEarliestValue(stockValueOnThisLine);
						}
						if (dateOnThisLine.after(company.getLatestDate())) {
							company.setLatestDate(dateOnThisLine);
							company.setLatestValue(stockValueOnThisLine);
						}
						companies.put(companyNameOnThisLine, company);
					}
					//Update the largestIncreaseValue and its corresponding company accordingly
					
				}	
			}
			//Loop through all company and find out the biggest increase
			
			for (int i = 0; i < companies.size(); i++) {
				Company company = companies.get(i);
				double largestIncreaseForThisCompany = company.getLargestIncreaseValue();
				if (largestIncreaseForThisCompany > largestIncreaseValue) {
					largestIncreaseValue = largestIncreaseForThisCompany;
					largestIncreaseCompany = company.getName();
				}
			}
			//Print out the result
			System.out.println("Largest Absolute Increase of Stock Value: " + largestIncreaseValue
					+ "\nCompany: " + largestIncreaseCompany);	
			
		}catch(ParseException pe) {
			System.out.print("ParseException: error in parsing date.");
		}catch(FileNotFoundException fnfe) {
			System.out.print("FileNotFoundException: File is not found.");
		}catch(IOException ioe) {
			System.out.println("IOException: occurs");
		}finally {
			if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
	}

}
