import dot.AddBlogRequest;
import dot.AddBlogResponse;
import dot.Blog;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;

@WebServlet(name="AddBlog", urlPatterns="/AddBlog")
public class AddBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        PrintWriter out = resp.getWriter();

        JSONObject reqJSONObject = getJSONObject(req.getReader());

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");



        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        AddBlogRequest addBlogRequest = new AddBlogRequest();
        Field[] field = addBlogRequest.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组
        for(int j=0 ; j<field.length ; j++){ //遍历所有属性
            String name = field[j].getName(); //获取属性的名字

            System.out.println("attribute name:"+name);
            String nameUp = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = field[j].getGenericType().toString(); //获取属性的类型
            try {
                Method m = addBlogRequest.getClass().getMethod("set"+nameUp, new Class[] {String.class});
                if(type.equals("class java.lang.String")){ //如果type是类类型，则前面包含"class "，后面跟类名
                    m.invoke(addBlogRequest,new Object[] {reqJSONObject.getString(name)});
                }
                if(type.equals("class java.lang.Integer")){
                    m.invoke(addBlogRequest,new Object[] {reqJSONObject.getInt(name)});
                }
                if(type.equals("class java.lang.Short")){
                    m.invoke(addBlogRequest,new Object[] {reqJSONObject.getInt(name)});
                }
                if(type.equals("class java.lang.Double")){
                    m.invoke(addBlogRequest,new Object[] {reqJSONObject.getDouble(name)});
                }
                if(type.equals("class java.lang.Boolean")){
                    m.invoke(addBlogRequest,new Object[] {reqJSONObject.getBoolean(name)});
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        Blog blog = new Blog();
        blog.setSubscriberId(addBlogRequest.getSubscriberId());
        blog.setCategory(addBlogRequest.getCategory());
        blog.setBlogTitle(addBlogRequest.getBlogTitle());
        blog.setBlog(addBlogRequest.getBlog());
        sqlSession.insert("AddBlog", blog);
        sqlSession.commit();

        AddBlogResponse addBlogResponse = new AddBlogResponse();
        addBlogResponse.setReturnCode("000000000");
        addBlogResponse.setReturnMessage("AddBlog success!");

        resp.setStatus(200);
        String jsonString = JSONObject.fromObject(addBlogResponse).toString();
        System.out.println(jsonString);
//        out.write(jsonString);
//
//        out.println(resp);
        out.println(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private JSONObject getJSONObject(BufferedReader reader) {
        StringBuffer buffer = new StringBuffer();
        String line = null;
        try {
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONObject.fromObject(buffer.toString());
    }

}
