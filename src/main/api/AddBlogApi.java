package main.api;

import main.dot.AddBlogRequest;
import main.dot.AddBlogResponse;

public interface AddBlogApi {
    AddBlogResponse addBlog (AddBlogRequest request);
}
