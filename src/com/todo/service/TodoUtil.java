package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n" + "추가할 항목의 제목을 입력하세요 > ");
		title = sc.next().trim();
		
		if (list.isDuplicate(title)) {
			System.out.println("제목 [" + title + "] 이 이미 존재합니다!");
			return;
		}
		
		sc.nextLine();
		System.out.print("추가할 항목의 내용을 입력하세요 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		
		System.out.println("새로운 항목이 추가되었습니다.");
	}

	
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n" + "삭제할 항목의 제목을 입력하세요 > ");
		String title = sc.next().trim();
		
		if (!l.isDuplicate(title)) {
			System.out.println("제목 [" + title + "] 이 존재하지 않습니다!");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("[" + title + "] 제목의 항목이 삭제되었습니다.");
				break;
			}
		}
	}

	
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n" + "수정할 항목의 제목을 입력하세요 > ");
		String title = sc.next().trim();
		
		if (!l.isDuplicate(title)) {
			System.out.println("제목 [" + title + "] 이 존재하지 않습니다!");
			return;
		}

		System.out.print("새로운 제목을 입력하세요 > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("제목 [" + new_title + "] 이 이미 존재합니다!");
			return;
		}
		
		sc.nextLine();
		System.out.print("새로운 내용을 입력하세요 > ");
		String new_description = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다.");
			}
		}

	}

	
	public static void listAll(TodoList l) {
		System.out.println(":: 전체 목록 ::");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	
	public static void saveList(TodoList l, String filename){
		try {
			Writer w = new FileWriter(filename);
			int count = 0;
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
				count ++;
			}
			w.close();
			
			System.out.println(count + "개의 항목이 파일에 저장되었습니다.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void loadList(TodoList l, String filename) throws IOException{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = "";
			int count = 0;
			while (( line = reader.readLine() )!= null){
				StringTokenizer str = new StringTokenizer(line, "##");
				TodoItem t = new TodoItem(str.nextToken(), str.nextToken(), str.nextToken());
				l.addItem(t);
				count ++;
			}
			System.out.println(filename + " 에서 " + count + "개의 데이터를 불러왔습니다.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(filename + " 파일이 존재하지 않습니다.");
			return;
		}
	}
}
