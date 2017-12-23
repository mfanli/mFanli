package main.api;

import dot.AddBlogRequest;
import dot.AddBlogResponse;

public interface AddBlogApi {
    AddBlogResponse addBlog (AddBlogRequest request);
}
