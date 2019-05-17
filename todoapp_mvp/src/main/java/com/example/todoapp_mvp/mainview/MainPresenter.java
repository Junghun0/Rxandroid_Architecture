package com.example.todoapp_mvp.mainview;

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
    public void start() {
        loadTasks(false);
    }
}
