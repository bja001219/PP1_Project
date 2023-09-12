package com.mycom.word;

public class Word {
    private int Id;
    private int level;
    private String word;
    private String meaning;

    Word(){}
    Word(int Id, int level, String word, String meaning){
        this.Id = Id;
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        String slevel = "";
        for(int i = 0; i <level ; i++) {
            slevel += "*";
        }
        String str = slevel + "  " + word + "  " + meaning;
        return str;
    }

    public String toFileString(){
        return this.level + "|" + this.word + "|" + this.meaning;
    }

}