//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.List;
//
//public class test {
//    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
////        String resource = "sqlMapConfig.xml";
////        InputStream inputStream = Resources.getResourceAsStream(resource);
////        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
////        SqlSession sqlSession = sqlSessionFactory.openSession();
////        List<Object> personList = sqlSession.selectList("User.findAll");
////        System.out.println(personList);
//
//
//        Class c = Class.forName("test1"); //包名为interview
//        Method m = c.getMethod("print", String.class); //Sigleton有一个方法为print
//        m.invoke(c.newInstance(), "helen"); //调用print方法
//    }
//}
