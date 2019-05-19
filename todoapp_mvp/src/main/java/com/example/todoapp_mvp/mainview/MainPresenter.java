package com.example.todoapp_mvp.mainview;

import com.example.todoapp_mvp.data.Task;

import androidx.annotation.NonNull;

public class MainPresenter implements MainContractor.Presenter{

    private MainContractor.View mMainView;

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(boolean forceUpdate) {

    }

    @Override
    public void addNewTask() {

    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {

    }

    @Override
    public void completeTask(@NonNull Task completedTask) {

    }

    @Override
    public void activateTask(@NonNull Task activeTask) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void start() {
        loadTasks(false);
    }
}
