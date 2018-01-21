package main.dot.mysql;

import java.sql.Timestamp;

public class Blog {
    private String subscriberId;
    private String id;
    private Timestamp createTime;
    private String category;
    private String blogTitle;
    private String blog;

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "{subscriberId=" + subscriberId +
                ", category=" + category +
                ", blogTitle=" + blogTitle +
                ", blog=" + blog +
                "}";
    }
}
