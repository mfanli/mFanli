import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@WebServlet(name = "Inteface", urlPatterns = {"/AddBlog", "/GetBlog", "/GetBlogByCategory", "/GetBlogByTime"})
public class Entry extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置返回属性
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = resp.getWriter();

        String servletPath = req.getServletPath();

        // 校验是否实现了接口
//        boolean hasInterface = Analysis.analysisXml().contains(servletPath);
//        if (!hasInterface) {
//            Result result = new Result();
//            result.setRetCode("125000001");
//            result.setRetMsg("No such interface!");
//            out.println(JSONObject.fromObject(result).toString());
//        }

        JSONObject reqJSONObject = getJSONObject(req.getReader());

//        实例化接口
        String className = "java.lang.String";
        try {
//            实例化人参对象，取代AddBlogRequest addBlogRequest = new AddBlogRequest();
            String servletInterface = servletPath.substring(1, servletPath.length());
            String servletRequest = servletInterface + "Request";
            String servletResponse = servletInterface + "Response";

//            实例化
            Object reqObj = Class.forName("main.dot." + servletRequest).newInstance();
            //Object respObj = Class.forName("main.dot." + servletResponse).newInstance();



//        AddBlogRequest addBlogRequest = new AddBlogRequest();
//            对bean对象赋值
            Field[] field = reqObj.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { //遍历所有属性
                String name = field[j].getName(); //获取属性的名字

                System.out.println("attribute name:" + name);
                String nameUp = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
                String type = field[j].getGenericType().toString(); //获取属性的类型


                if (!reqJSONObject.containsKey(name))
                {
                    continue;
                }
                if (type.equals("class java.lang.String")) { //如果type是类类型，则前面包含"class "，后面跟类名
//                    执行set方法
                    Method m = reqObj.getClass().getMethod("set" + nameUp, new Class[]{String.class});
                    m.invoke(reqObj, new Object[]{reqJSONObject.getString(name)});
                }
                if (type.equals("class java.lang.Integer")) {
                    //m.invoke(reqObj, new Object[]{reqJSONObject.getInt(name)});

                    Method m = reqObj.getClass().getMethod("set" + nameUp, new Class[]{Integer.class});
                    //终端传入都为String，此处需要转换类型。
                    m.invoke(reqObj, new Object[]{Integer.valueOf(reqJSONObject.getString(name))});
                }
                if (type.equals("class java.lang.Short")) {
                    Method m = reqObj.getClass().getMethod("set" + nameUp, new Class[]{String.class});
                    m.invoke(reqObj, new Object[]{reqJSONObject.getInt(name)});
                }
                if (type.equals("class java.lang.Double")) {
                    Method m = reqObj.getClass().getMethod("set" + nameUp, new Class[]{String.class});
                    m.invoke(reqObj, new Object[]{reqJSONObject.getDouble(name)});
                }
                if (type.equals("class java.lang.Boolean")) {
                    Method m = reqObj.getClass().getMethod("set" + nameUp, new Class[]{String.class});
                    m.invoke(reqObj, new Object[]{reqJSONObject.getBoolean(name)});
                }
            }

//            Class.forName(className).getConstructor(String.class).newInstance("main.spi." + servletInterface.substring(0, 1).toLowerCase() + "Spi");
            String classPath = "main.spi." + servletInterface + "Spi";
            Class c = Class.forName(classPath); //包名
            String methodName = servletInterface.substring(0, 1).toLowerCase() + servletInterface.substring(1);
            Method m = c.getMethod(methodName, reqObj.getClass()); //Sigleton有一个方法
            Object respObj = m.invoke(c.newInstance(), reqObj); //调用接口方法

            resp.setStatus(200);
            String jsonString = JSONObject.fromObject(respObj).toString();
            out.println(jsonString);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONObject.fromObject(buffer.toString());
    }

}
