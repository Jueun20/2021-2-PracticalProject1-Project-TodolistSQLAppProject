package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<TodoList 관리 명령어>");
        System.out.println(" add - 새로운 항목 추가하기");
        System.out.println(" del - 기존의 항목 삭제하기");
        System.out.println(" edit - 기존의 항목 수정하기");
        System.out.println(" find <keyword> - 키워드를 포함한 항목 출력하기");
        System.out.println(" find_cate <keyword> - 키워드를 포함한 카테고리 출력하기");
        System.out.println(" ls - 리스트 전체 불러오기");
        System.out.println(" ls_cate - 저장된 카테고리 출력하기");
        System.out.println(" ls_name_asc - 항목들 제목순으로 정렬하기");
        System.out.println(" ls_name_desc - 항목들 제목역순으로 정렬하기");
        System.out.println(" ls_date - 항목들 날짜순으로 정렬하기");
        System.out.println(" ls_date_desc - 항목들 날짜순으로 정렬하기");
        System.out.println(" exit - 프로그램 종료하기");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
