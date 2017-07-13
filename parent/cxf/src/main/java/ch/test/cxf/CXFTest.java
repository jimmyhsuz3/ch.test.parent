package ch.test.cxf;
import java.util.ArrayList;
import java.util.List;
import com.e104.mongo.MongoService00;
import com.e104.pdd.JobRecomm00;
import com.e104.plus.service.impl.AccessRecordService00;
import com.e104.plus.service.impl.AccessRecordServiceException_Exception;
import demo.spring.service.HelloWorldImplService;
public class CXFTest {
	private List<String> list = new ArrayList<String>();
	public List<String> testMongoService(){
		try {
			list.add(new MongoService00().getMongoService00HttpEndpoint().select("mdb0c00038", "jimmy_test", "{id:2}"));
		} catch (RuntimeException re){
			re.printStackTrace();
			list.add(re.getLocalizedMessage());
		}
		list.add(new MongoService00().getMongoService00HttpSoap11Endpoint().select("mdb0c00038", "jimmy_test", "{id:2}"));
		list.add(new MongoService00().getMongoService00HttpSoap12Endpoint().select("mdb0c00038", "jimmy_test", "{id:2}"));
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