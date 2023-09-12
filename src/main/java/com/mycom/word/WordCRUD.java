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
        System.out.print("=> 새 단어 입력 : ");
        String word = s.nextLine();
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        return new Word(0, level, word, meaning);
    }

    public void addItem() {
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

    public void listAll() {
        System.out.println("---------------------------------");
        for(int i = 0; i< list.size(); i++) {
            System.out.print((i+1)+ " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("---------------------------------");
    }
    public ArrayList<Integer> listAll(String keyword) {
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

    public void searchLevel() {
        System.out.println("=> 원하는 레벨은? (1~3): ");
        int level = s.nextInt();
        int j = 0;
        ArrayList<Integer> idlist = new ArrayList<>();
        System.out.println("---------------------------------");
        for(int i = 0; i< list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if(ilevel == level){
                System.out.print((j+1)+ " ");
                System.out.println(list.get(i).toString());
                j++;
            }
        }
        System.out.println("---------------------------------");
    }
    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.println("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.println("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id - 1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }


    public void deleteItem() {
        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.println("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.println("=> 정말로 삭제하시겠습니까?(y/n) : ");
        while(true){
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
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt"));
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