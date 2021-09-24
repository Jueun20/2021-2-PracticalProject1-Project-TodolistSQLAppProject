package com.todo;

import java.io.IOException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt");
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String[] input = sc.nextLine().trim().split(" ");
			String choice = input[0];
			String keyWord = "";
			if (input.length == 2)
				 keyWord = input[1];
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "find":
				TodoUtil.findItem(l, keyWord);
				break;
				
			case "find_cate":
				TodoUtil.findCate(l, keyWord);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
			
			case "ls_cate":
				TodoUtil.listAllCate(l);
				break;
				
			case "ls_name_asc":
				l.sortByName();
				System.out.println("리스트의 항목들을 제목순으로 정렬하였습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				System.out.println("리스트의 항목들을 제목의 역순으로 정렬하였습니다.");
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("리스트의 항목들을 날짜순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				System.out.println("리스트의 항목들을 날짜의 역순으로 정렬하였습니다.");
				l.reverseList();
				isList = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("명령어가 올바르지 않습니다.");
				System.out.println("(도움말 - help)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		System.out.println("프로그램이 종료되었습니다.");
		TodoUtil.saveList(l, "todolist.txt");
	}
}
