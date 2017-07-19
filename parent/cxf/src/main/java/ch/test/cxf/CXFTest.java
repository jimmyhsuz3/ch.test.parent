package ch.test.cxf;
import java.util.ArrayList;
import java.util.List;
import com.e104.mongo.MongoService00;
import com.e104.pdd.JobRecomm00;
import com.e104.plus.service.impl.AccessRecordService00;
import com.e104.plus.service.impl.AccessRecordServiceException_Exception;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import demo.spring.service.HelloWorldImplService;
public class CXFTest {
	private List<String> list = new ArrayList<String>();
	public static void main(String[] args){
		System.out.println(new CXFTest().testMongoServiceProfileView());
	}
	private String testMongoServiceProfileView(){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.createObjectNode().set("$and", mapper.createArrayNode()
				.add(mapper.createObjectNode().set("pid", mapper.createObjectNode().put("$gt", 100000)))
				.add(mapper.createObjectNode().set("pid", mapper.createObjectNode().put("$lt", 200000))));
		String jsonReturn = new MongoService00().getMongoService00HttpSoap11Endpoint().select("mdb0f00001", "ProfileView", node.toString());
		ArrayNode ary = mapper.createArrayNode();
		String JsonTestStr = "{\"name\":\"JsonTest\"}";
		try {
			JsonTest jsonTest = mapper.readValue(JsonTestStr, JsonTest.class);
			ary.addPOJO(mapper.writeValueAsString(jsonTest));
			ary.addPOJO(mapper.readTree(JsonTestStr));
			JsonNode rootNode = mapper.readTree(jsonReturn);
			for (int i = 0; i < rootNode.size(); i++)
				ary.add(rootNode.get(i).get("pid").asLong());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ary.toString();
	}
	public List<String> testMongoService(){
		try {
			list.add(new MongoService00().getMongoService00HttpEndpoint().select("mdb0c00038", "jimmy_test", "{id:2}"));
		} catch (RuntimeException re){
			re.printStackTrace();
			list.add(re.getLocalizedMessage());
		}
		list.add(new MongoService00().getMongoService00HttpSoap11Endpoint().select("mdb0c00038", "jimmy_test", "{id:2}"));
		list.add(new MongoService00().getMongoService00HttpSoap12Endpoint().select("mdb0c00038", "jimmy_test", "{id:2}"));
		list.add(testMongoServiceProfileView());
		return list;
	}
	public String testHelloWorld(){
		try {
			return new HelloWorldImplService().getHelloWorldImplPort().sayHi("hsiang");
		} catch (RuntimeException re){
			re.printStackTrace();
			return re.getLocalizedMessage();
		}
	}
	public List<String> testJobRecomm(){
		try {
			list.add(new JobRecomm00().getJobRecomm00HttpEndpoint().jobTitleAC("dd"));
		} catch (RuntimeException re){
			re.printStackTrace();
			list.add(re.getLocalizedMessage());
		}
		list.add(new JobRecomm00().getJobRecomm00HttpSoap11Endpoint().jobTitleAC("dd"));
		list.add(new JobRecomm00().getJobRecomm00HttpSoap12Endpoint().jobTitleAC("dd"));
		return list;
	}
	public List<String> testAccessRecord(){
		try {
			list.add(new AccessRecordService00().getAccessRecordService00HttpEndpoint().viewProfileFromPro("pk", 1l, 1l));
		} catch (AccessRecordServiceException_Exception e) {
			e.printStackTrace();
			list.add(e.getLocalizedMessage());
		} catch (RuntimeException re){
			re.printStackTrace();
			list.add(re.getLocalizedMessage());
		}
		try {
			list.add(new AccessRecordService00().getAccessRecordService00HttpSoap11Endpoint().viewProfileFromPro("pk", 1l, 1l));
		} catch (AccessRecordServiceException_Exception e) {
			e.printStackTrace();
			list.add(e.getLocalizedMessage());
		} catch (RuntimeException re){
			re.printStackTrace();
			list.add(re.getLocalizedMessage());
		}
		try {
			list.add(new AccessRecordService00().getAccessRecordService00HttpSoap12Endpoint().viewProfileFromPro("pk", 1l, 1l));
		} catch (AccessRecordServiceException_Exception e) {
			e.printStackTrace();
			list.add(e.getLocalizedMessage());
		} catch (RuntimeException re){
			re.printStackTrace();
			list.add(re.getLocalizedMessage());
		}
		return list;
	}
}