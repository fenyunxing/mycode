package com.example.contractlist;

class Person {
    String word=null;
    String name=null;
    int image;

    public Person(String word, String name, int image) {
        this.word = word;
        this.name = name;
        this.image = image;
    }

    public int getImage() {
        return image;
    }



    public String getWord() {
        return word;
    }


    public String getName() {
        return name;
    }

}
