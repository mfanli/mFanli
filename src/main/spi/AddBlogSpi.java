package main.spi;

import dot.AddBlogRequest;
import dot.AddBlogResponse;
import dot.mysql.Blog;
import main.mySql.MySql;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class AddBlogSpi {
    public static AddBlogResponse addBlog(AddBlogRequest request) {
        AddBlogResponse response = new AddBlogResponse();
        try {
            //        获取数据库会话
            SqlSession sqlSession = MySql.getSqlSession();

            Blog blog = new Blog();
            blog.setSubscriberId(request.getSubscriberId());
            blog.setCategory(request.getCategory());
            blog.setBlogTitle(request.getBlogTitle());
            blog.setBlog(request.getBlog());
            sqlSession.insert("AddBlog", blog);
            sqlSession.commit();

            response.setRetCode("000000000");
            response.setRetMsg("hello world lalal");
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
