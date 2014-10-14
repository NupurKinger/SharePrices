import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class CSVParserTest {

//	blank file
//	zero data rows
//	zero company columns
//	spaces in month names
//  spaces in stock prices
//	incorrect separator
//	2 maximum values -- handle again
//	blank value for a company
//	2 companies having same name
//	year and month incorrect header
//	negative stock price
//	extra element in a row
//	less elements in a row
//	non integer value in data
//  extra blank line
//  invalid file name
//  price outside range
//  jumbled header elements
	
	//company name blank
	
	
	@Test
	public void testNormalCase() {
		System.out.println("\nNormal case ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample1.csv";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Company-A", "Mar 2000");
		map.put("Company-B", "Mar 2007");
		map.put("Company-C", "Jun 1993");
		map.put("Company-D", "Apr 2002");
		map.put("Company-E", "Oct 2008");
		assertEquals(new TreeMap<String, String>(CSVParser.parse(fileName)), map);
	}

	@Test
	public void testSingleDataRow() {
		System.out.println("\nSingle data row ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample2.csv";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Company-A", "Jan 1990");
		map.put("Company-B", "Jan 1990");
		map.put("Company-C", "Jan 1990");
		map.put("Company-D", "Jan 1990");
		map.put("Company-E", "Jan 1990");
		assertEquals(new TreeMap<String, String>(CSVParser.parse(fileName)), map);
	}

	@Test
	public void testNegativeData() {
		System.out.println("\nNegative data ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample5.csv";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Company-A", "Mar 2000");
		map.put("Company-B", "Mar 2007");
		map.put("Company-C", "Jun 1993");
		map.put("Company-D", "Apr 2002");
		map.put("Company-E", "Oct 2008");
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testBlankFile() {
		System.out.println("\nBlank file ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample4.csv";
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testDuplicateYearHeading() {
		System.out.println("\nDuplicate year heading ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample3.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testDuplicateMonthHeading() {
		System.out.println("\nDuplicate month heading ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample11.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testNoData() {
		System.out.println("\nNo stock price data ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample6.csv";
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testNoCompanyName() {
		System.out.println("\nNo company name in header ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample7.csv";
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testInvalidMonthName() {
		System.out.println("\nInvalid month name ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample8.csv";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Company-A", "Mar 2000");
		map.put("Company-B", "Mar 2007");
		map.put("Company-C", "Ju n 1993");
		map.put("Company-D", "Apr 2002");
		map.put("Company-E", "Oct 2008");
		assertEquals(new TreeMap<String, String>(CSVParser.parse(fileName)), map);
	}
	
	@Test
	public void testInvalidPrice() {
		System.out.println("\nInvalid price ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample9.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testInvalidSeparator() {
		System.out.println("\nInvalid separator ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample10.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testDuplicateHighestPrice() {
		System.out.println("\nDuplicate highest price ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample12.csv";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Company-A", "Mar 2000");
		map.put("Company-B", "Mar 2007");
		map.put("Company-C", "Jun 1993");
		map.put("Company-D", "Apr 2002");
		map.put("Company-E", "Oct 2008, Sep 2012");
		assertEquals(new TreeMap<String, String>(CSVParser.parse(fileName)), map);
	}

	@Test
	public void testBlankPrice() {
		System.out.println("\nBlank price ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample13.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testDuplicateCompanyName() {
		System.out.println("\nDuplicate company name ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample14.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testInvalidYearOrMonth() {
		System.out.println("\nInvalid year or month ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample15.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testExtraPricesInRow() {
		System.out.println("\nExtra prices in row ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample16.csv";
		assertNull(CSVParser.parse(fileName));
	}

	@Test
	public void testMissingPricesInRow() {
		System.out.println("\nMissing prices in row ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample17.csv";
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testInvalidPrice2() {
		System.out.println("\nInvalid price2 ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample18.csv";
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testBlankLine() {
		System.out.println("\nBlank line ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample19.csv";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Company-A", "Mar 2000");
		map.put("Company-B", "Mar 2007");
		map.put("Company-C", "Jun 1993");
		map.put("Company-D", "Apr 2002");
		map.put("Company-E", "Oct 2008");
		assertEquals(new TreeMap<String, String>(CSVParser.parse(fileName)), map);
	}

	@Test
	public void testInvalidFileName() {
		System.out.println("\nInvalid file name ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample.csv";
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testJumbledHeader() {
		System.out.println("\nJumbled headings ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample20.csv";
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Company-A", "Mar 2000");
		map.put("Company-B", "Mar 2007");
		map.put("Company-C", "Jun 1993");
		map.put("Company-D", "Apr 2002");
		map.put("Company-E", "Oct 2008");
		assertEquals(new TreeMap<String, String>(CSVParser.parse(fileName)), map);
	}

	@Test
	public void testOutsideRangePrice() {
		System.out.println("\nOutside range price ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample21.csv";
		assertNull(CSVParser.parse(fileName));
	}
	
	@Test
	public void testMissingCompanyName() {
		System.out.println("\nMissing company name ->");
		String fileName = "C:\\Users\\nupur\\workspace\\SharePrices\\src\\sample22.csv";
		assertNull(CSVParser.parse(fileName));
	}
}
