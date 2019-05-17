package com.example.todoapp_mvp;

public interface BaseView<T> {
    void setPresenter(T presenter);
}
