package main.dot;

import main.dot.mysql.Blog;

public class GetBlogByIdResponse {
    private Result result;
    private Blog[] blogs;

    public Blog[] getBlogs() {
        return blogs;
    }

    public void setBlogs(Blog[] blogs) {
        this.blogs = blogs;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
