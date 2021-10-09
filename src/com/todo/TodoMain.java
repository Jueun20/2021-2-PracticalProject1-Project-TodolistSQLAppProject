package com.todo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException, SQLException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		
		boolean isList = false;
		boolean quit = false;
		
		//TodoUtil.loadList(l, "todolist.txt");
		
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
				TodoUtil.findList(l, keyWord);
				break;
				
			case "find_cate":
				TodoUtil.findCateList(l, keyWord);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
			
			case "ls_cate":
				TodoUtil.listAllCate(l);
				break;
				
			case "ls_name":
				System.out.println("리스트의 항목들을 제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 1);
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("리스트의 항목들을 제목의 역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 0);
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("리스트의 항목들을 날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 1);
				isList = true;
				break;
				
			case "ls_date_desc":
				System.out.println("리스트의 항목들을 날짜의 역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 0);
				isList = true;
				break;
				
			case "comp":
				int index = Integer.parseInt(keyWord);
				TodoUtil.completeItem(l, index);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				
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
			
		} while (!quit);
		System.out.println("프로그램이 종료되었습니다.");
		TodoUtil.saveList(l, "todolist.txt");
	}
}
