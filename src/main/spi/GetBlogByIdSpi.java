package main.spi;

import main.dot.GetBlogByIdRequest;
import main.dot.GetBlogByIdResponse;
import main.dot.Result;
import main.dot.mysql.Blog;
import main.mySql.MySql;
import main.spi.constants.ErrorCode;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class GetBlogByIdSpi {
    public static GetBlogByIdResponse getBlog(GetBlogByIdRequest request)
    {
        GetBlogByIdResponse getBlogByIdResponse = new GetBlogByIdResponse();
        Result result = new Result(ErrorCode.SUCCESS, "GetBlogById success!");

        try
        {
            // 获取数据库会话
            SqlSession sqlSession = MySql.getSqlSession();;

            List<Blog> blogs = sqlSession.selectList("getBlogBySubscriberId");
            sqlSession.commit();

            getBlogByIdResponse.setBlogs((Blog[])blogs.toArray());

        } catch (IOException e){
            e.printStackTrace();
            result.setRetCode(ErrorCode.SELECT_BLOG_FAIL);
            result.setRetMsg("getBlogById failed!");
        } catch (Exception e){
            e.printStackTrace();
            result.setRetCode(ErrorCode.SELECT_BLOG_FAIL);
            result.setRetMsg("system error!");
        }

        getBlogByIdResponse.setResult(result);
        return getBlogByIdResponse;
    }
}
