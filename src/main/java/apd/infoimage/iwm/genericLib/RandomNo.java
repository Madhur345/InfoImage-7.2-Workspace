package apd.infoimage.iwm.genericLib;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.Assert;

public class RandomNo {
	/**
	 * This method generates random number.
	 * @author KencharV.
	 * @return random number.
	 */
	public String randomNoGenerator(){
	try{
		Random r = new Random();
		List<Integer> codes = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
		{
		    int x = r.nextInt(9999999);
		    while (codes.contains(x))
		        x = r.nextInt(9999999);
		    codes.add(x);
		}
		String str = String.format("%04d", codes.get(0));
		return str;
	}
	catch(NumberFormatException numberFormatException)
	{
	System.out.println(" Number format is wrong"); 
	  Assert.fail(" Number format is wrong");
	   return null;
	}
	
	catch (NullPointerException nullPointerException) {
		 System.out.println(" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
 			Assert.fail("you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
 			return null;
	} 
	
	catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
		// TODO: handle exception
		 System.out.println(" trying to retrive/access data morethan/negative-size than actual  size  ");
		 Assert.fail(" trying to retrive/access  data morethan/negative than actual  size   ");
		 return null;
	}
	   
	catch (Exception e) {
		// TODO: handle exception
		 System.out.println(e.getMessage());
		 System.out.println(" error occured while generating random number ");
		 Assert.fail("error occured while generating random number  ");
		 return null;
	}
	
	}
	
	
	/**
	 *  Description : This method will generate random number of specified size max 10 digit. 
	 *  @param length
	 *  @author KencharV
	 */
	public String getRandomNo(int length){
		try
		{
			  final String characters = "1234567890";
		       StringBuilder result = new StringBuilder();
		       while(length > 0) {
		           Random rand = new Random();
		           result.append(characters.charAt(rand.nextInt(characters.length())));
		           length--;
		       }
		       return result.toString();
		}
		catch(NumberFormatException numberFormatException)
		{
			System.out.println(" Number format is wrong"); 
			Assert.fail(" Number format is wrong");
			return null;
		}

		catch (NullPointerException nullPointerException) {
			System.out.println(" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail("you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			return null;
		} 

		catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			// TODO: handle exception
			System.out.println(" trying to retrive/access data morethan/negative-size than actual  size  ");
			Assert.fail(" trying to retrive/access  data morethan/negative than actual  size   ");
			return null;
		}

		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(" error occured while generating random number ");
			Assert.fail("error occured while generating random number  ");
			return null;
		}


	}
	
	
	
	/**
	 *  Description : This method will generate random string value contains only alpha characters. 
	 *  @param length
	 *  @author KencharV
	 */
	
	public String getRandomString(int length) {
		String chars= "abcdefghijklmnopqrstuvwxyz";
		
		
		  Random rand = new Random();
		  StringBuilder buf = new StringBuilder();
		  for (int i=0; i<length; i++) {
		    buf.append(chars.charAt(rand.nextInt(chars.length())));
		  }
		  
		  System.out.println("ch"+buf.toString());
		return buf.toString();
	

}
	
	

	/**
	 *  Description : This method will generate random string value contains  alpha and numeric characters. 
	 *  @param length
	 *  @author KencharV
	 */
	
	public String getRandomAlphaNumericString(int length) {
	       final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLMNOPQRSTUVWXYZ1234567890";
	       StringBuilder result = new StringBuilder();
	       while(length > 0) {
	           Random rand = new Random();
	           result.append(characters.charAt(rand.nextInt(characters.length())));
	           length--;
	       }
	       return result.toString();
	    }
	
	
	/**
	 *  Description : This method will generate random string value contains  alpha and numeric and special character . 
	 *  @param length
	 *  @author KencharV
	 */
	
	public String getRandomAlphaNumericSpecialCharacter(int length) {
	       final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLMNOPQRSTUVWXYZ1234567890~!@#$%^&*";
	       StringBuilder result = new StringBuilder();
	       while(length > 0) {
	           Random rand = new Random();
	           result.append(characters.charAt(rand.nextInt(characters.length())));
	           length--;
	       }
	       return result.toString();
	    }
	
	


	

}
