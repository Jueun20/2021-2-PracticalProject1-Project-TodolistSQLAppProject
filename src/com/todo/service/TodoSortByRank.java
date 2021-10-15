package com.todo.service;
import java.util.Comparator;

import com.todo.dao.TodoItem;

public class TodoSortByRank implements Comparator<TodoItem> {

    @Override
    public int compare(TodoItem o1, TodoItem o2) {
        return o1.getRank().compareTo(o2.getRank());

    }
}
