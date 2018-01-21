package main.spi;

import main.dot.GetBlogByTimeRequest;
import main.dot.GetBlogByTimeResponse;
import main.dot.Result;
import main.dot.mysql.Blog;
import main.dot.mysql.SelectBlogByTime;
import main.mySql.MySql;
import main.spi.constants.ErrorCode;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class GetBlogByTimeSpi {
    public static GetBlogByTimeResponse getBlogByTime (GetBlogByTimeRequest request){
        GetBlogByTimeResponse response = new GetBlogByTimeResponse();
        Result result = new Result(ErrorCode.SUCCESS, "GetBlogByTime success!");

        try
        {
            SelectBlogByTime selectBlogByTime = new SelectBlogByTime();
            selectBlogByTime.setSubscriberId(request.getSubscriberId());
            if (StringUtils.isNotBlank(request.getStartTime()))
            {
                selectBlogByTime.setStartTime(new Timestamp(Long.valueOf(request.getStartTime())));
            }
            if (StringUtils.isNotBlank(request.getEndTime()))
            {
                selectBlogByTime.setEndTime(new Timestamp(Long.valueOf(request.getEndTime())));
            }

            selectBlogByTime.setCount(request.getCount());

            // 获取数据库会话
            SqlSession sqlSession = MySql.getSqlSession();

            //List<Blog> blogs = sqlSession.selectList("getBlogByTime", selectBlogByTime);
            Object[] blogsObj = sqlSession.selectList("getBlogByTime", selectBlogByTime).toArray();
            sqlSession.commit();

            response.setResult(new Result(ErrorCode.SUCCESS, "GetBlogByTime success!"));

            if (ArrayUtils.isNotEmpty(blogsObj))
            {
                int length = blogsObj.length;
                Blog[] blogsResp = new Blog[length];
                for (int i = 0; i < length; i++) {
                    blogsResp[i] = (Blog) blogsObj[i];
                }
                response.setBlogs(blogsResp);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            result.setRetCode(ErrorCode.SELECT_BLOG_FAIL);
            result.setRetMsg("getBlogById failed!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.setRetCode(ErrorCode.SELECT_BLOG_FAIL);
            result.setRetMsg("system error!");
        }

        response.setResult(result);
        return response;
    }
}
