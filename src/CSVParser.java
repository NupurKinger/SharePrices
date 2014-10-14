import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CSVParser {

	public static final String SEPARATOR = ",";
	
	public static Map<String, String> parse(String fileName) {
		
		Map<String, String> result = null;
		
		// A map of index to list of company details including company name, highest stock price so far and corresponding year and month
		Map<Integer, List<String>> tempMap = new HashMap<Integer, List<String>>(); 
		
		String yearString = "Year";
		String monthString = "Month";
		int yearIndex = -1;
		int monthIndex = -1;
		
		BufferedReader br = null;
		String line = "";
	 
		try {
	 
			br = new BufferedReader(new FileReader(fileName));
			
			// Extract headers and indexes
			if ((line = br.readLine()) != null) {
	
				String[] headings = line.split(SEPARATOR);
				
				// Check validity of header
				if(line.equals("") || headings.length < 2) {
					throw new Exception("Invalid file format");
				}
				
				// Check duplication in header
				for(int i = 1 ; i<headings.length ; i++) {
					for(int j = i-1 ; j>=0 ; j--) {
						if(headings[i].equalsIgnoreCase(headings[j])) {
							throw new Exception("Invalid header format - Duplicate headings present");
						}
					}
				}
				
				
				for(int i = 0 ;i<headings.length ; i++) {
					if(headings[i].equalsIgnoreCase(yearString)) {
						// Extracting year index
						yearIndex = i;
					} else if(headings[i].equalsIgnoreCase(monthString)) {
						// Extracting month index
						monthIndex = i;
					} else {
						List<String> list = new ArrayList<String>();
						if(headings[i].equals("")) {
							throw new Exception("Company name is blank");
						}
						list.add(headings[i]);	// Company name
						list.add("-1");			// Highest stock price
						list.add("");			// Corresponding year and month
						tempMap.put(i, list);
					}
				}
			} else {
				throw new Exception("Blank CSV file");
			}
			
			if(tempMap.size() == 0) {
				throw new Exception("No company name found in the header");
			}
			if(monthIndex == -1 || yearIndex == -1) {
				throw new Exception("Year or Month or both missing in the header");
			}
			
			// Purpose of flag is to check if there are any prices at all
			Boolean flag = Boolean.FALSE;
			while ((line = br.readLine()) != null) {
				
				if(line.equals("")) {	// Avoid and continue functioning if blank line is encountered
					continue;
				}
				
				flag = Boolean.TRUE;
				String[] elements = line.split(SEPARATOR);
				if(elements.length != (tempMap.size() + 2)) {
					// Extra or lesser price values as compared to company names
					throw new Exception("Incorrect number of values encountered");
				}
				
				for(int i = 0 ;i<elements.length ; i++) {
					if(i!=monthIndex && i!=yearIndex) {
						try {
							if(elements[i].equals("")) {
								throw new Exception("Blank stock price encountered");
							} else if(new Integer(elements[i])<0) {
								throw new Exception("Negative stock price encountered");
							} else if(new Integer(elements[i]) > new Integer(tempMap.get(i).get(1))) {
								tempMap.get(i).set(1, elements[i]);											// Stock price
								tempMap.get(i).set(2, elements[monthIndex] +" " + elements[yearIndex]);	    // Space separated month and year
							} else if(new Integer(elements[i]).equals(new Integer(tempMap.get(i).get(1)))) {
								tempMap.get(i).set(2, tempMap.get(i).get(2) + ", " + elements[monthIndex] +" " + elements[yearIndex]);	    // Comma separated multiple year and month
							}
						} catch (NumberFormatException e) {
							throw new Exception("Illegal stock price encountered");
						}
					} 
				}
			}
			if(flag == Boolean.FALSE) {
				throw new Exception("No stock price data found");
			}
	 
			// Extracting values from tempMap to create result map
			result = new HashMap<String, String>();
			for(Integer i : tempMap.keySet()) {
				result.put(tempMap.get(i).get(0), tempMap.get(i).get(2));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error : Invalid file path or name");
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	public static void main(String args[]) {
		Map<String, String> map = parse("C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample12.csv");
		for(String s : map.keySet()) {
			System.out.print(s + " -> ");
			System.out.println(map.get(s));
		}
	}
}
