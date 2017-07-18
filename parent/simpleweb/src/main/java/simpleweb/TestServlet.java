package simpleweb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch.test.axis2.Axis2Test;
import ch.test.http.ProfileSearchEngineTest;
import ch.test.mongodb.MongodbTest;
import ch.util.crc.CRCTest;
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 9158993975266397633L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Model> modelList = new ArrayList<Model>();
//		<!-- HJAAM+HM -->
//		modelList.add(new Model("CXFTest.testHelloWorld", new CXFTest().testHelloWorld()));
//		modelList.add(new Model("CXFTest.testJobRecomm", new CXFTest().testJobRecomm()));
//		modelList.add(new Model("CXFTest.testAccessRecord", new CXFTest().testAccessRecord()));
//		modelList.add(new Model("CXFTest.testMongoService", new CXFTest().testMongoService()));
		modelList.add(new Model("Axis2Test.testHello", new Axis2Test().testHelloWorld()));
		modelList.add(new Model("Axis2Test.testJobRecomm", new Axis2Test().testJobRecomm()));
		modelList.add(new Model("Axis2Test.testAccessRecord", new Axis2Test().testAccessRecord()));
		modelList.add(new Model("Axis2Test.testMongoService", new Axis2Test().testMongoService()));
		modelList.add(new Model("ProfileSearchEngineTest.testProfileSearchEngine", new ProfileSearchEngineTest().testProfileSearchEngine()));
		modelList.add(new Model("MongodbTest.testMongodb", new MongodbTest().testMongodb()));
		req.setAttribute("modelList", modelList);
		req.setAttribute("crcList", new CRCTest().test());
		req.getRequestDispatcher("test.jsp").forward(req, resp);
	}
	public class Model {
		private String title;
		private List<String> list = new ArrayList<String>();
		private Model(String title, List<String> list) {
			this.title = title;
			this.list.addAll(list);
		}
		private Model(String title, String resp){
			this.title = title;
			this.list.add(resp);
		}
		public String getTitle() {return title;}
		public List<String> getList() {return list;}
	}
}