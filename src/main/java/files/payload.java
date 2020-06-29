package files;

public class payload {
	public static String AddPlace()
	{
		
		
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Rahul Shetty Academy\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"6, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"https://rahulshettyacademy.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}";
		
	}
	public static String passwordLoginBody(String userName) {

		return "{\r\n"
		        + "\"username\":\"" + userName + "\",\r\n"
				+ "   \"password\":\"QGa3QefpcVaY/4DPABKEC4yIX9kJ90rnxiIDs49tFNgwWcvG391fb8oa8OfTC0+FSuLlY5quC5mostDvyZV0Bme+Hd9ln2800pBS9QHS4KywxYeUN3cXjpkP2bGeWzlA/3aPmLEtPW7G7wThI1ZEUFCswaSLms5AnW0NPfMxOXw=\"\r\n"
				+ "}";

	}

	public static String resetPasswordBody(String userName) {

		return "{\r\n"
		        + "\"username\":\"" + userName + "\",\r\n"
				+ "   \"password\":\"\"\r\n"
				+ "}";

	}

	public static String changePinBody(String userName) {

		return "{\r\n"
				+ "  \"pin\": \"Vw\\/+6jiCLGMywV+ESuriq\\/Rkx8YjYGJeuXI0Q+wGz1typv3HcUpOpdTOtt9VFL625ikBu7f2PRafYjMIK9NooBD\\/ipjGFWLo9pHxRva9C2TgywgiUhBby7lOvVyllQw0K4\\/MEZLG9pDB\\/lRZw8YZ3ptbAS9D9Oyr0n6wGlsBqVI=\",\r\n"
				+ "  \"username\":\"" + userName + "\"\r\n" 
				+ "}";

	}
}
