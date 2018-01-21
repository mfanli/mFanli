package main.spi;

import main.common.Sequence;
import main.dot.AddBlogRequest;
import main.dot.AddBlogResponse;
import main.dot.Result;
import main.dot.mysql.Blog;
import main.mySql.MySql;
import main.spi.constants.ErrorCode;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBlogSpi {
    public static AddBlogResponse addBlog(AddBlogRequest request) {
        AddBlogResponse response = new AddBlogResponse();
        try {
            // 获取数据库会话
            SqlSession sqlSession = MySql.getSqlSession();

            Blog blog = new Blog();
            blog.setSubscriberId(request.getSubscriberId());
            blog.setId(Sequence.nextId());

            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//将时间格式转换成符合Timestamp要求的格式.
            Timestamp goodsC_date =Timestamp.valueOf(nowTime);//把时间转换
            blog.setCreateTime(goodsC_date);

            blog.setCategory(request.getCategory());
            blog.setBlogTitle(request.getBlogTitle());
            blog.setBlog(request.getBlog());

            sqlSession.insert("AddBlog", blog);
            sqlSession.commit();


            response.setResult(new Result(ErrorCode.SUCCESS, "AddBlog success!"));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
