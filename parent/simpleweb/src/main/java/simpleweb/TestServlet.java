package simpleweb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch.util.crc.CRCTest;
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 9158993975266397633L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Model> modelList = new ArrayList<Model>();
//		modelList.add(new Model("EndpointTest.testHelloWorld", new EndpointTest().testHelloWorld()));
//		modelList.add(new Model("EndpointTest.testJobRecomm", new EndpointTest().testJobRecomm()));
//		modelList.add(new Model("EndpointTest.testAccessRecord", new EndpointTest().testAccessRecord()));
//		modelList.add(new Model("Axis2Test.testHello", new Axis2Test().testHelloWorld()));
//		modelList.add(new Model("Axis2Test.testAccessRecord", new Axis2Test().testAccessRecord()));
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