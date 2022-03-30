package ru.mmalakhova.nsu.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "completed", columnDefinition = "BOOLEAN")
    private Boolean completed = Boolean.FALSE;
    @Column(name = "todo_order")
    private Integer order;
    private String url;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? url : url + "/" + id;
    }

    public Todo(Integer id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Todo() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed == null ? Boolean.FALSE : completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(title, todo.title) && Objects.equals(completed, todo.completed) && Objects.equals(order, todo.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, completed, order);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order=" + order +
                '}';
    }
}
