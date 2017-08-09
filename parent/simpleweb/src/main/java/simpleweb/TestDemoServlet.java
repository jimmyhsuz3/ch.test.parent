package simpleweb;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch.test.axis2.DemoTest;
public class TestDemoServlet extends HttpServlet {
	private static final long serialVersionUID = -6081243697304065124L;
	private final DemoTest demoTest = new DemoTest();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String userName = req.getParameter("userName");
		String articleId = req.getParameter("articleId");
		String content = req.getParameter("content");
		resp.setHeader("Content-Type", "text/plain; charset=utf-8");
		if ("getArticle".equals(action)){
			String respone = demoTest.getArticle(userName, Long.parseLong(articleId));
			System.out.println(respone);
			resp.getWriter().print(respone);
		}else if ("getArticles".equals(action))
			resp.getWriter().print(demoTest.getArticles(userName));
		else if ("saveArticle".equals(action))
			resp.getWriter().print(demoTest.saveArticle(userName, content));
		else if ("updateArticle".equals(action))
			resp.getWriter().print(demoTest.updateArticle(userName, Long.parseLong(articleId), content));
	}
}