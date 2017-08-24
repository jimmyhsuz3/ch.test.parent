package ch.test.cxf;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.HttpException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class BeachBoyTest {
	private String simpleSearchAPI = "http://172.19.9.106/custprofile/SimpleSearch.json";
	public static void main(String[] args){
		BeachBoyTest test = new BeachBoyTest();
		String jsonSearchString = test.jsonSearchString("6000000", "68090000");
		String freeIndexSearch = test.freeIndexSearch(jsonSearchString);
		String simpleSearchAPI = test.simpleSearchAPI(null, jsonSearchString);
		System.out.println(nodeEqual(toNode(freeIndexSearch).get("Result"), toNode(simpleSearchAPI).get("Result")));
	}
	private static JsonNode toNode(String json){
		System.out.println(json);
		try {
			return new ObjectMapper().readTree(json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private static boolean nodeEqual(JsonNode node1, JsonNode node2){
		if (node1.isArray() && node2.isArray()){
			for (int i1 = 0; i1 < node1.size(); i1++){
				boolean equals = false;
				for (int i2 = 0; i2 < node2.size(); i2++)
					if (node1.get(i1).equals(node2.get(i2))){
						equals = true;
						break;
					}
				if(!equals)
					return equals;
			}
			return true;
		} else {
			return node1.equals(node2);
		}
	}
	private String jsonSearchString(String... custNos){
		java.util.List<String> custNoList = java.util.Arrays.<String>asList(custNos);
		StringBuffer custNoStr = new StringBuffer("(");
		for (String custNo: custNoList)
			custNoStr.append(custNo).append(" ");
		custNoStr = custNoStr.deleteCharAt(custNoStr.length()-1);
		custNoStr.append(")");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("index", "custprofile");
		param.put("queryString", "RETURNID:"+custNoStr );
		param.put("sort", null);
		param.put("recFrom", 0);
		param.put("requestLength", custNoList.size());
		param.put("executeFrom", "10400");
		param.put("fieldMap", new String[] { "RETURNID","NAME","LOGO"});
		String jsonSearchString;
		try {
			jsonSearchString = new ObjectMapper().writeValueAsString(param);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		System.out.println(jsonSearchString);
		return jsonSearchString;
	}
	private String freeIndexSearch(String jsonSearchString){
		return new rdd.BeachBoy00().getBeachBoy00HttpSoap11Endpoint().freeIndexSearch(jsonSearchString);
	}
	private String simpleSearchAPI(String productKey, String jsonSearchString) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		JsonNode root;
		try {
			root = mapper.readTree(jsonSearchString);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (Iterator<Entry<String, JsonNode>> iter = root.fields(); iter.hasNext();){
			Entry<String, JsonNode> entry = iter.next();
			String key = entry.getKey();
			if (key.equals("queryString"))
				key = "mainQuery";
			if (key.equals("fieldMap"))
				key = "displayField";
			node.set(key, entry.getValue());
			if (key.equals("index") && "custprofile".equals(entry.getValue().asText()))
				node.put(key, "custProfile");
		}
		try {
			jsonSearchString = mapper.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("Q", jsonSearchString);
		System.out.println("simpleSearchAPI = " + paramMap);
		try {
			return HttpPostClient.postMethod(simpleSearchAPI, paramMap);
		} catch (HttpException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}