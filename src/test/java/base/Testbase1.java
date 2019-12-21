package base;

import org.testng.annotations.BeforeSuite;

public class Testbase1 
{
	public static String DBIP;
	public static String DBSCHEMA;
	@BeforeSuite
	public void beforeSuite(){
		DBIP  = "10.67.200.35";
		DBSCHEMA  = "adeledb";
	}
}
