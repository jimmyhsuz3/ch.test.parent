package ch.test.axis2;
import java.io.IOException;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import com.e104.aop.DemoServiceStub;
import com.e104.aop.GetArticleDocument;
import com.e104.aop.GetArticleDocument.GetArticle;
import com.e104.aop.GetArticlesDocument;
import com.e104.aop.GetArticlesDocument.GetArticles;
import com.e104.aop.SaveArticleDocument;
import com.e104.aop.SaveArticleDocument.SaveArticle;
import com.e104.aop.UpdateArticleDocument;
import com.e104.aop.UpdateArticleDocument.UpdateArticle;
import com.fasterxml.jackson.core.JsonProcessingException;
public class DemoTest {
	private final DemoServiceStub stub;
	public static void main(String[] args){
		DemoTest demoTest = new DemoTest();
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		long articleId;
		try {
			articleId = mapper.readTree(demoTest.saveArticle("demoTester", "文章開頭,<a href='???'>標籤的內容</a>,文章結尾"))
					.get("response").get("pojo").asLong();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(demoTest.getArticle("demoTester", articleId));
	}
	public DemoTest(){
		try {
			stub = new DemoServiceStub();
		} catch (AxisFault e) {
			throw new RuntimeException(e);
		}
	}
	public String getArticle(String userName, long articleId){
		GetArticleDocument doc = GetArticleDocument.Factory.newInstance();
		GetArticle getArticle = doc.addNewGetArticle();
		getArticle.setArgs0(userName);
		getArticle.setArgs1(articleId);
		try {
			return stub.getArticle(doc).getGetArticleResponse().getReturn();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	public String getArticles(String userName){
		GetArticlesDocument doc = GetArticlesDocument.Factory.newInstance();
		GetArticles getArticles = doc.addNewGetArticles();
		getArticles.setArgs0(userName);
		try {
			return stub.getArticles(doc).getGetArticlesResponse().getReturn();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	public String saveArticle(String userName, String content){
		SaveArticleDocument doc = SaveArticleDocument.Factory.newInstance();
		SaveArticle saveArticle = doc.addNewSaveArticle();
		saveArticle.setArgs0(userName);
		saveArticle.setArgs1(content);
		try {
			return stub.saveArticle(doc).getSaveArticleResponse().getReturn();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	public String updateArticle(String userName, long articleId, String content){
		UpdateArticleDocument doc = UpdateArticleDocument.Factory.newInstance();
		UpdateArticle updateArticle = doc.addNewUpdateArticle();
		updateArticle.setArgs0(userName);
		updateArticle.setArgs1(articleId);
		updateArticle.setArgs2(content);
		try {
			return stub.updateArticle(doc).getUpdateArticleResponse().getReturn();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
}