package simpleweb;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch.util.crc.GitTest;
public class TestGitServlet extends HttpServlet {
	private static final long serialVersionUID = -6081243697304065124L;
	private final GitTest gitTest = new GitTest();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print(gitTest.testByDate(req.getParameter("strFrom"), req.getParameter("strTo")));
	}
}