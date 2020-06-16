package com.apitesting.listners;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 *  Json Path Hello World
 *
  */
public class JsonPathHelloWorld {

    public  static String print (String json ,String subscription) {
JSONArray msisdnHash = JsonPath.read(json, "$.subscriptions[0].[?(@.[\"msisdn\"]==\"447741739979\")].msisdnHash");
		
		
		System.out.println("msisdnHash: " +msisdnHash.toString());
/*
        List<Map<String, Object>> expensiveBooks = JsonPath
                .using(Configuration.defaultConfiguration())
                .parse(json)
                .read("$.store.book[?(@.price > 22)].title", List.class);

        System.out.println(expensiveBooks); // print ["Hello, Middle-earth! "]

        System.out.println();
        String jsonHiWorld = "{\"message\":\"Hi\",\"place\":{\"name\":\"World!\"}}\"";
        String message = JsonPath.read(jsonHiWorld, "$.message");
        String place = JsonPath.read(jsonHiWorld, "$.place.name");
        System.out.println(message + " " + place); // print "Hi World!"
*/	
        return msisdnHash.toJSONString();

    }

    private final static String json = "{\r\n" + 
    		"    \"status\": {\r\n" + 
    		"        \"code\": 0,\r\n" + 
    		"        \"analytics\": {\r\n" + 
    		"            \"status\": \"SUCCESS\",\r\n" + 
    		"            \"guid\": \"e92106af-436c-4128-b74a-2c43d642a101\"\r\n" + 
    		"        }\r\n" + 
    		"    },\r\n" + 
    		"    \"lastAPICall\": \"2020-05-21T17:16:35\",\r\n" + 
    		"    \"subscriptions\": [\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739979\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"5359fc338c63a11c5aa2f2437f54879f0c3995803efada45ec88f8c3c2ce0272\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739992\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"9d83b8a0748c04d2d0d31d3dbdeb3b71973030435426c97555acacb2f74eb82b\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739984\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"f3aef01b18f465e6f592471e57dccb06f0cb3936716424615f45e4670908fea6\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739991\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"a04230ef1497ba538b2081defa30c1fffcd7566b73f874e4df4ac841f844f681\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739982\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"36e1ef52ccc8511075f00c2e267567119e9eb2f9b97b2083ed7bd64d2667c17f\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739988\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"a2d80b03ded3e2a4653e9a33194db4ba4596a6ca9fac129e247e415b83a90f47\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739997\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"5c10e90e80caa79ad1630966ab8f5efb919ad15ea0fa17bf1b2078f7b2aa7535\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739989\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"54c2998fa7f1c31070e88c97ab31c3e6feb5edc52f118791e65ae91bebaccc50\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739987\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"800f6650598bad999897583e1dd20027e19e70f15b7a5882e42c3f387c0fd084\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739994\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"29a433f35f7eaae29b264dd63cda9af2e9139ff5d4a51c11e8ed1007e966d2bf\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739998\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"a0c1807eae67c18583b1406d407499760fb497ae38cd7163a2d4383a0bf39a8e\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739985\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"9230c7483f8c8d02c15e938e852893ca05db2fe99cb0683004c7b3a1121c6fa4\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739993\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"a22ebd77d02965e3d3af64b5e9ddc9e1108939a62d27abc94cbed2bbbb5571e6\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739990\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"48e674df49934f7345de4aafc1596914a116784a5d95a117ed293e546e6841cd\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739996\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"1e45c3151409363686d38a2917481e6db2b07a1c57292ee2eed5631239ee43b6\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739983\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"34f40227985973169c6af4739c06727e6da0ca6ad46d9b9c40f7bcd77c35cd68\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739986\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MPS\",\r\n" + 
    		"                \"iconId\": \"1085\",\r\n" + 
    		"                \"riderText\": \"Pay monthly\"\r\n" + 
    		"            },\r\n" + 
    		"            \"contact\": \"%Phonebook_lookup%\",\r\n" + 
    		"            \"msisdnHash\": \"7b83e929e53378341f355a416c465082c79c5d708cb92a114c3162032e2b338e\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739981\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"11ea42e986bd92c021573fd05da90e4dd824aa1c635bba27cb19b46dd124b09f\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739980\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"0ed4366ab2a9b1774409f1ed14f90c2475757f1e2fd709fc5385d483268a6c71\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        },\r\n" + 
    		"        {\r\n" + 
    		"            \"msisdn\": \"447741739995\",\r\n" + 
    		"            \"segment\": \"CONSUMER\",\r\n" + 
    		"            \"accountId\": \"7000369054\",\r\n" + 
    		"            \"backgroundType\": \"FLOWER_BACKGROUND\",\r\n" + 
    		"            \"subscriptionType\": {\r\n" + 
    		"                \"name\": \"MBB\",\r\n" + 
    		"                \"iconId\": \"1086\",\r\n" + 
    		"                \"riderText\": \"Mobile Broadband\"\r\n" + 
    		"            },\r\n" + 
    		"            \"msisdnHash\": \"93079b1c20497fc3a1de60fd25d675964139621b7238f69af408a324f50e5237\",\r\n" + 
    		"            \"target\": \"HOME\",\r\n" + 
    		"            \"callSubsSwitchAPI\": true\r\n" + 
    		"        }\r\n" + 
    		"    ]\r\n" + 
    		"}";
}
