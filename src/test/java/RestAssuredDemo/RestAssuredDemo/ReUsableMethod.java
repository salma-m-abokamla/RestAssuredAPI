package RestAssuredDemo.RestAssuredDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class ReUsableMethod {


	public static String returnMsisdnHashValue(String result,String subscription) {
		
		JSONArray msisdnHash = JsonPath.parse(result).read("$.subscriptions[?(@.msisdn==\""+subscription+"\")].msisdnHash");

		String otac = "";
		String regex = "[a-zA-Z0-9]+";

		Pattern pattern = Pattern.compile(regex);
		Matcher m1 = pattern.matcher(msisdnHash.toString());
		if (m1.find()) {
			otac = m1.group(0);
			//System.out.print("msisdnHash:" + otac);
		}
		return otac;

	}

}
