package ch.test.axis2;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import com.e104.mongo.MongoService00Stub;
import com.e104.mongo.SelectDocument;
import com.e104.mongo.SelectDocument.Select;
import com.e104.pdd.JobRecomm00Stub;
import com.e104.pdd.JobTitleACDocument;
import com.e104.pdd.JobTitleACResponseDocument;
import com.e104.plus.service.impl.AccessRecordServiceExceptionException;
import com.e104.plus.service.impl.AccessRecordServiceStub;
import com.e104.plus.service.impl.QueryActivityTotalPvDocument;
import com.e104.plus.service.impl.ViewProfileFromProDocument;
import com.e104.plus.service.impl.ViewProfileFromProDocument.ViewProfileFromPro;
import com.e104.plus.service.impl.ViewProfileFromProResponseDocument;
import com.e104.plus.service.impl.QueryActivityTotalPvDocument.QueryActivityTotalPv;
import demo.spring.service.GetDocument;
import demo.spring.service.HelloWorldImplServiceStub;
import demo.spring.service.SayHiDocument;
import demo.spring.service.XRayType;
public class Axis2Test {
	public static void main(String[] args){
		System.out.println(new Axis2Test().testHelloWorld());
		System.out.println(new Axis2Test().testJobRecomm());
        System.out.println(new Axis2Test().testAccessRecord());
        System.out.println(new Axis2Test().testMongoService());
	}
	public String testMongoService(){
		StringBuilder builder = new StringBuilder();
		try {
			MongoService00Stub stub = new MongoService00Stub();
			SelectDocument sDoc = SelectDocument.Factory.newInstance();
			Select select = sDoc.addNewSelect();
			select.setJSONQuery("{}");
			select.setDBname("mdb0c00038");
			select.setCollectionname("jimmy_test");
			builder.append(stub.select(sDoc).getSelectResponse().getReturn());
		} catch (AxisFault e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		}
		return builder.toString();
	}
	public String testHelloWorld(){
		StringBuilder builder = new StringBuilder();
		try {
			HelloWorldImplServiceStub stub = new HelloWorldImplServiceStub();
			SayHiDocument doc = SayHiDocument.Factory.newInstance();
			doc.addNewSayHi().setText("hsiang");
			builder.append(stub.sayHi(doc).getSayHiResponse().getReturn());
		} catch (AxisFault e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		}
		builder.append('\n');
		try {
			HelloWorldImplServiceStub stub = new HelloWorldImplServiceStub();
			// stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			GetDocument doc = GetDocument.Factory.newInstance();
			doc.addNewGet().setFilePath("C:\\Users\\jimmy.shu\\Desktop\\test_vm_share\\TortoiseSVN-1.9.5.27581-x64-svn-1.9.5.msi");
			long start = System.currentTimeMillis();
			XRayType type = stub.get(doc).getGetResponse().getReturn();
			long end = System.currentTimeMillis();
			builder.append(type.getPatientName()).append('\n').append(type.getImageData().length);
			builder.append('\n').append(end - start).append("ms");
		} catch (AxisFault e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		}
		return builder.toString();
	}
	public String testJobRecomm(){
		StringBuilder builder = new StringBuilder();
		try {
			JobRecomm00Stub stub = new JobRecomm00Stub();
			JobTitleACDocument doc = JobTitleACDocument.Factory.newInstance();
			doc.addNewJobTitleAC().setJobtitle("dd");
			JobTitleACResponseDocument resp = stub.jobTitleAC(doc);
			builder.append(resp.getJobTitleACResponse().getReturn());
		} catch (AxisFault e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		}
		return builder.toString();
	}
	public String testAccessRecord(){
		StringBuilder builder = new StringBuilder();
		try {
			AccessRecordServiceStub stub = new AccessRecordServiceStub();
			ViewProfileFromProDocument vDoc = ViewProfileFromProDocument.Factory.newInstance();
			ViewProfileFromPro vOper = vDoc.addNewViewProfileFromPro();
			vOper.setProductKey("pk");
			vOper.setCid(1l);
			vOper.setTargetPid(1l);
			ViewProfileFromProResponseDocument vResp = stub.viewProfileFromPro(vDoc);
			builder.append(vResp.getViewProfileFromProResponse().getReturn());
			builder.append('\n');
			QueryActivityTotalPvDocument qDoc = QueryActivityTotalPvDocument.Factory.newInstance();
			QueryActivityTotalPv qOper = qDoc.addNewQueryActivityTotalPv();
			qOper.setProductKey("ppp");
			qOper.addAids("");
			builder.append(stub.queryActivityTotalPv(qDoc).getQueryActivityTotalPvResponse().getReturn());
		} catch (AxisFault e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		} catch (AccessRecordServiceExceptionException e) {
			e.printStackTrace();
			builder.append(e.getLocalizedMessage());
		}
		return builder.toString();
	}
}