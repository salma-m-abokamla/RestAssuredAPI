package files;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class RunnableClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestListenerAdapter tla = new TestListenerAdapter();
	    TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
	    suites.add("C:\\Users\\ElMetwally2S\\Documents\\RestAssuredAPI4\\testng.xml");//path to xml..
	    testng.setTestSuites(suites);
	    testng.run();
	}

}
