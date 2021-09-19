package com.todo.service;

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
}
