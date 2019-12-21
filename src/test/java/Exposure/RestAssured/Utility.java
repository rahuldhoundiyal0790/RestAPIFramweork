package Exposure.RestAssured;

import org.apache.commons.lang3.RandomStringUtils;

public class Utility {
	public static String getNameofSwimlane()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(1);
		return ("NAMETEST"+generatedString);
		
	}
	public static String getExternalIdofSwimlane()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(1);
		return ("EXTERNALTEST"+generatedString);
		
	}
	public String randomStringGenerator()
	{
		// chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"; 
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(20); 
          for (int i = 0; i < 20; i++) { 
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
             int index  = (int)(AlphaNumericString.length() * Math.random()); 
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString.charAt(index)); 
        } 
         System.out.println(sb.toString());
        return sb.toString(); 
	}
	public static void main(String[] args) 
    { 
		Utility ut=new Utility();
		ut.randomStringGenerator();
    } 
}
