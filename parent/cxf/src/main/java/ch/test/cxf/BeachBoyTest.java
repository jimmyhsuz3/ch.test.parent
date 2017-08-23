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
		String jsonSearchString = test.jsonSearchString("11111");
		System.out.println(test.freeIndexSearch(jsonSearchString));
		System.out.println(test.simpleSearchAPI(null, jsonSearchString));
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
			node.set(key, entry.getValue());
		}
		try {
			jsonSearchString = mapper.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("Q", jsonSearchString);
		System.out.println("simpleSearchAPI = " + jsonSearchString);
		try {
			return HttpPostClient.postMethod(simpleSearchAPI, paramMap);
		} catch (HttpException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}