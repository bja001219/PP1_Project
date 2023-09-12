package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;


    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도 1,2,3 중에 입력 : ");
        int level = s.nextInt();
        s.nextLine();
        System.out.print("=> 새 단어 입력 : ");
        String word = s.nextLine();
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        return new Word(0, level, word, meaning);
    }

    public void addItem() { //위의 add 사용하여 4번 메뉴 실행하기
        Word a = (Word)add();
        list.add(a);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {
    }

    public void listAll() { //1번 메뉴의 모든 단어 보여주는 기능실행
        System.out.println("---------------------------------");
        for(int i = 0; i< list.size(); i++) {
            System.out.print((i+1)+ " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("---------------------------------");
    }
    public ArrayList<Integer> listAll(String keyword) { //사용자에게 입력받은 키워드를 통해 작업 할 때 필요
        int j = 0;
        ArrayList<Integer> idlist = new ArrayList<>();
        System.out.println("---------------------------------");
        for(int i = 0; i< list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)){
                continue;
            }
            System.out.print((j+1)+ " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("---------------------------------");
        return idlist;
    }

    public void searchLevel() { //2번 메뉴 실행을 위해 필요
        System.out.println("=> 원하는 레벨은? (1~3): ");
        int level = s.nextInt(); //사용자에게 레벨을 받고
        int j = 0;
        ArrayList<Integer> idlist = new ArrayList<>();
        System.out.println("---------------------------------");
        for(int i = 0; i< list.size(); i++) { 
            int ilevel = list.get(i).getLevel();
            if(ilevel == level){ //레벨이 일치하는 단어들 출력해주기
                System.out.print((j+1)+ " ");
                System.out.println(list.get(i).toString());
                j++;
            }
        }
        System.out.println("---------------------------------");
    }
    public void updateItem() { // 5번 메뉴 실행 위해 함수 만들기
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword); //위의 listAll 키워드를 사용하여 사용자가 원하는 단어 찾기
        System.out.println("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.println("=> 뜻 입력 : "); //이후 뜻만 따로 입력받아 수정하기
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id - 1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }


    public void deleteItem() { //6번 메뉴 실행 위한 함수
        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.println("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.println("=> 정말로 삭제하시겠습니까?(y/n) : ");
        while(true){ //y와 n외의 글자를 입력하면 둘 중 하나 입력할때까지 while 돌리기
            String ans = s.next();
            if(ans.equals("y")||ans.equals("Y")) {
                list.remove((int)idlist.get(id - 1));
                System.out.println("단어가 삭제되었습니다. ");
                break;
            } else if(ans.equals("N")||ans.equals("n")){
                System.out.println("취소되었습니다. ");
                break;
            } else{
                System.out.println("y 혹은 n 중에 입력해주세요 (y/n).");
            }
        }
    }

    final String file = "Dictionary.txt";
    public void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;
            while(true) {
                line = br.readLine();
                if(line == null){
                    break;
                }
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0,level, word, meaning));
                count++;
            }
            br.close();
            System.out.println(file+ "에서 단어 " + count + "개 데이터 로딩 완료");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("Dictionary.txt"));
            for (Word one : list){
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("==> 'test'에 데이터 저장 완료.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchWord() {
        System.out.println("=> 검색 할 단어를 작성해주세요(단어의 일부분도 가능): ");
        String keyword = s.next();
        listAll(keyword);
    }





}