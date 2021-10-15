package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	Connection conn;
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n" + "추가할 항목의 제목을 입력하세요 > ");
		title = sc.next().trim();
		
		if (l.isDuplicate(title)) {
			System.out.println("제목 [" + title + "]이 이미 존재합니다!");
			return;
		}
		
		sc.nextLine();
		System.out.print("추가할 항목의 카테고리를 입력하세요 > ");
		category = sc.next().trim();
		
		sc.nextLine();
		System.out.print("추가할 항목의 내용을 입력하세요 > ");
		desc = sc.nextLine().trim();

		System.out.print("추가할 항목의 마감 날짜를 입력하세요(YYYY/MM/DD) > ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		if (l.addItem(t) > 0)
			System.out.println("새로운 항목이 추가되었습니다.");
	}

	
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n" + "삭제할 항목의 번호를 입력하세요 > ");
		
		String numbers = sc.nextLine();
		
		int count = 0;
		for (int i = 0; i < numbers.length(); i ++) {
			if (numbers.charAt(i) == ',')
				count ++;
		}
		
		if (count == 0) {
			int index = Integer.parseInt(numbers);
			if (l.deleteItem(index) > 0) {
				System.out.println(index + "번 항목이 삭제되었습니다.");
				return;
			}
		}
		else {
			int chck = 0;
			
			String[] arry = numbers.split(", ");
			for (String num : arry) {
				int index = Integer.parseInt(num);
				if (l.deleteItem(index) > 0) 
					chck ++;
			}
			
			if (chck != 0)
				System.out.println("총 " + chck + "개의 항목이 삭제되었습니다.");
		}
		
		
		/*
		ArrayList list = l.getList();
		int count = list.size();
		
		if (num < 0 || num > list.size()) {
			System.out.println("번호가 잘못되었습니다!");
			return;
		}
		
		int index = num - 1;
		TodoItem deliItem = (TodoItem) list.get(index);
		System.out.print(num + "번 항목을 정말로 삭제할까요?(Y/N) > ");
		String answer = sc.next().trim();
		
		if(answer.contains("Y")) {
			l.deleteItem(deliItem);
			System.out.println(num + "번 항목이 삭제되었습니다.");
		}

		for (TodoItem item : l.getList()) {
			if (title.equals(title)) {
				l.deleteItem(item);
				System.out.println(num + "번 항목이 삭제되었습니다.");
				break;
			}
		}
		*/
	}

	
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n" + "수정할 항목의 번호를 입력하세요 > ");
		int index = sc.nextInt();

		System.out.print("새로운 제목을 입력하세요 > ");
		String new_title = sc.next().trim();
		/*
		if (l.isDuplicate(new_title)) {
			System.out.println("제목 [" + new_title + "] 이 이미 존재합니다!");
			return;
		}
		*/
		sc.nextLine();
		System.out.print("새로운 카테고리를 입력하세요 > ");
		String new_cate = sc.next().trim();
		
		sc.nextLine();
		System.out.print("새로운 내용을 입력하세요 > ");
		String new_description = sc.nextLine().trim();

		System.out.print("새로운 마감 날짜를 입력하세요(YYYY/MM/DD) > ");
		String new_due = sc.next().trim();
		/*
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				//l.deleteItem(item);
				TodoItem t = new TodoItem(new_cate, new_title, new_description, new_due);
				l.editItem(item, t);
				System.out.println("수정되었습니다.");
			}
		}
		*/
		TodoItem t = new TodoItem(new_cate, new_title, new_description, new_due);
		t.setId(index);
		if (l.updateItem(t) > 0)
			System.out.println(index + "번 항목이 수정되었습니다.");
	}
	
	public static void completeItem(TodoList l, String numbers) {
		
		int count = 0;
		for (int i = 0; i < numbers.length(); i ++) {
			if (numbers.charAt(i) == ',')
				count ++;
		}
		
		if (count == 0) {
			int index = Integer.parseInt(numbers);
			if (l.completeItem(index) > 0) {
				System.out.println(index + "번 항목이 완료되었습니다.");
				return;
			}
		}
		
		else {
			int chck = 0;
			
			String[] arry = numbers.split(", ");
			for (String num : arry) {
				int index = Integer.parseInt(num);
				if (l.completeItem(index) > 0) 
					chck ++;
			}
			
			if (chck != 0)
				System.out.println("총 " + chck + "개의 항목이 완료되었습니다.");
		}
	}
	
	public static void rankItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		String total;
		
		System.out.print("[우선순위]\n" + "우선 순위 1-3위 순서대로 번호를 입력하세요(n, n, n) > ");
		total = sc.nextLine().trim();
		
		int count = 0;
		
		String[] rank = total.split(", ");
		for (int i = 0; i < 3; i ++) {
			int index = Integer.parseInt(rank[i]);
			count = l.rankItem(index, i + 1);
		}
		if (count > 0)
			System.out.println("우선순위 설정이 완료되었습니다.");
	}
	
	public static void importanceItem(TodoList l, int index) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[중요도 표시]\n" + index + "번 항목의 중요도를 숫자로 입력하세요(1-5) > ");
		int score = sc.nextInt();
		
		if (l.importanceItem(index, score) > 0)
			System.out.println(index + "번 항목의 중요도는 " + score + "점입니다.");
	}
	
	public static void listAll(TodoList l) {
		System.out.printf(":: 전체 목록 (총 %d개) ::\n", l.getCount());
		/*
		for (TodoItem item : l.getList()) {
			int num = l.indexOf(item) + 1;
			System.out.print(num + ". ");
			System.out.println(item.toString());
		}
		*/
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf(":: 전체 목록 (총 %d개) ::\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	/*
	public static void findItem(TodoList l, String f) {
		int count = 0;
		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(f) || item.getDesc().contains(f)) {
				int num = l.indexOf(item) + 1;
				System.out.print(num + ". ");
				System.out.println(item.toString());
				count = count + 1;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	*/
	
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	
	public static void listAll(TodoList l, int comp) {
		int count = 0;
		for (TodoItem item : l.getList(comp)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	//getListRank
	public static void listAllRank(TodoList l, int num) {
		int count = 0;
		ArrayList<TodoItem> result = new ArrayList<TodoItem>();
		for (TodoItem item : l.getListRank(num)) {
			count ++;
			result.add(item);
		}
		
		Collections.sort(result, new TodoSortByRank());
		for (int i = 0; i < 3; i ++) {
			TodoItem t = result.get(i);
			System.out.println(t.getRank() + "위) " + t.toStringRank());
		}
		
		if (count == 0)
			System.out.println("아직 우선순위를 설정하지 않았습니다!");
	}
	
	
	/*
	public static void findCate(TodoList l, String f) {
		int count = 0;
		for (TodoItem item : l.getList()) {
			if (item.getCategory().contains(f)) {
				int num = l.indexOf(item) + 1;
				System.out.print(num + ". ");
				System.out.println(item.toString());
				count = count + 1;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	*/
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	/*	
	public static void listAllCate(TodoList l) {
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		ArrayList<String> arry = new ArrayList<String>();
		
		for (TodoItem item : l.getList()) {
			set.add(item.getCategory());
		}
		
		Iterator<String> iter = set.iterator();	
		while(iter.hasNext()) {
		    arry.add(iter.next());
		}
		
		for (int i = 0; i < set.size(); i ++) {
			if (i != set.size() - 1) {
				
				System.out.print(arry.get(i) + " / ");
			}
			else {
				System.out.println(arry.get(i));
			}
		}
		
		System.out.println("총 " + set.size() + "개의 카테고리가 등록되어 있습니다.");
	}
	*/
	public static void listAllCate(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
		}
		System.out.println("\n총 " + count + "개의 카테고리가 등록되어 있습니다.");
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
				TodoItem t = new TodoItem (str.nextToken(), str.nextToken(), str.nextToken(), str.nextToken(), str.nextToken(), str.nextToken());
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
