package com.example.todoapp_mvp.data.local;


import android.content.Context;

import com.example.todoapp_mvp.data.Task;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {
    //ToDoDatabase object를 생성하는 코드는 비용이 많이 들기때문에 싱글톤으로 구현해야됨

    private static ToDoDatabase INSTANCE;

    public abstract TasksDao taskDao();

    private static final Object sLock = new Object();

    public static ToDoDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ToDoDatabase.class, "Tasks.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
