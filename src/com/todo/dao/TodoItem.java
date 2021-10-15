package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    
    private String category;
    private String due_date;
    
    private int id;
    private String is_completed;
    
    private String rank;
    private String importance;

    public TodoItem(String category, String title, String desc, String due_date){
        this.title=title;
        this.desc=desc;
        this.category = category;
        this.due_date = due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
    }
    
    public TodoItem(String is_completed, String category, String title, String desc, String due_date, String date){
    	this.category = category;
        this.title=title;
        this.desc=desc;
        this.due_date = due_date;
        this.current_date = date;
        this.is_completed = is_completed;
    }
    
    public TodoItem(String importance, String rank, String is_completed, String category, String title, String desc, String due_date, String date){
    	this.category = category;
        this.title=title;
        this.desc=desc;
        this.due_date = due_date;
        this.current_date = date;
        this.is_completed = is_completed;
        this.importance = importance;
        this.rank = rank;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getDue_date() {
    	return due_date;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getIs_completed() {
    	return is_completed;
    }
    
    public void setIs_completed(String is_completed) {
    	this.is_completed = is_completed;
    }
    
    public String getRank() {
    	return rank;
    }
    
    public void setRank(String rank) {
    	this.rank = rank;
    }
    
    public void setImportance(String importance) {
    	this.importance = importance;
    }
    
    @Override
    public String toString() {
    	String star = "";
    	int count =  Integer.parseInt(this.importance);
    	
    	for (int j = 0; j < count; j ++) {
    		star = star + "*";
    	}
    		
    	for (int k = count; k < 5; k ++) {
    		star = star + " ";
    	}
    	
    	if (is_completed.contains("1")) 
    		return "V " + id + "[ranking:" + rank + "|" + star + "]" + " [" + category + "] " + "|" + title + "| " + desc + " (" + due_date + ")" + " - " + current_date;
    	
    	
    	return "  " + id + "[ranking:" + rank + "|" + star + "]" + " [" + category + "] " + "|" + title + "| " + desc + " (" + due_date + ")" + " - " + current_date;
    }
    
    public String toStringRank() {
    	return id + " [" + category + "] " + "|" + title + "| " + desc + " (" + due_date + ")" + " - " + current_date;
    }
    
    public String toSaveString() {
    	return importance + "##" + rank + "##" + is_completed + "##" + category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }
}
