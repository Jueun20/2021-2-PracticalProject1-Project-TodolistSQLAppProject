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
        System.out.println(" ls_name - 항목들 제목순으로 정렬하기");
        System.out.println(" ls_name_desc - 항목들 제목역순으로 정렬하기");
        System.out.println(" ls_date - 항목들 날짜순으로 정렬하기");
        System.out.println(" ls_date_desc - 항목들 날짜역순으로 정렬하기");
        System.out.println(" comp <num> - 항목 완료하기");
        System.out.println(" ls_comp - 완료된 항목만 출력하기");
        System.out.println(" have_to - 완료되지 않은 항목만 출력하기");
        System.out.println(" rank - 가장 중요한 세 가지 항목 우선순위 설정하기");
        System.out.println(" ls_rank - 우선순위 1-3위 항목 출력하기");
        System.out.println(" star <num> - 항목의 중요도 표시하기");
        System.out.println(" ls_star - 항목의 중요도 표시하기");
        System.out.println(" exit - 프로그램 종료하기");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
