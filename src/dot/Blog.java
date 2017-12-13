package dot;

public class Blog {
    private String subscriberId;
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

    public String toString() {
        return "{subscriberId=" + subscriberId +
                ", category=" + category +
                ", blogTitle=" + blogTitle +
                ", blog=" + blog +
                "}";
    }
}
