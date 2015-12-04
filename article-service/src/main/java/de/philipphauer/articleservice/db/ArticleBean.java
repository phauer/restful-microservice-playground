package de.philipphauer.articleservice.db;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("category", category)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleBean that = (ArticleBean) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category);
    }
}