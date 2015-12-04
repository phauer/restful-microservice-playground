package de.philipphauer.articleservice.db;

import org.springframework.data.annotation.Id;

public class ArticleBean {

    @Id
    private String id;

    private String name;

    private String category;

    public ArticleBean() {}

    public ArticleBean(String name, String category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(getClass().getSimpleName()+"[id=%s, name='%s', category='%s']", id, name, category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleBean that = (ArticleBean) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(category != null ? !category.equals(that.category) : that.category != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}