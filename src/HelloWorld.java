import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="HelloWorld", urlPatterns="/HelloWorld")
public class HelloWorld extends HttpServlet{
    private String message;

    @Override
    public void init() throws ServletException{
        message = "Hello world, this message is from servlet!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        String resource = "main/mySql/sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Object> personList = sqlSession.selectList("User.findAll");

        out.println("<h1>" + message + "</h1>" + personList);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
