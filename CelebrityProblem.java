package org.itstep;

import java.util.Stack;

//Задача о знаменитости:
//Требуется найти знаменитость на вечеринке за минимальное количество вопросов.
//Все знают знаменитость, а она не знает никого (а кто ее пригласил?)
public class CelebrityProblem {

    static int contacts[][] = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 1, 0}};
/*
    static int contacts[][] = {
            {1, 0, 1, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 1},
            {0, 0, 1, 0}};
     */
    static int count = contacts.length;

    public static void main(String[] args) {
        int c = findCelebrity();
        System.out.println("Celebrity: " + c);
    }

    static boolean knows(int a, int b) {
        return (contacts[a][b] == 1) ? true : false;
    }

    static boolean checkCelebrity(int c) {
        boolean result = true;
        for (int i = 0; i < count; i++)
            if (i != c && (knows(c, i) || !knows(i, c)))
                result = false;
        return result;
    }

    static int findCelebrity(){
        Stack<Integer> stack = new Stack<>();
        int c = -1;
        for (int i = 0; i < count; i++)
            stack.push(i);
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            if (knows(a, b)) stack.push(b);
            else stack.push(a);
        }
        if (stack.empty()) c = -1;
        else {
            c = stack.pop();
            if (!checkCelebrity(c)) c = -1;
        }
        return c;
    }

    //Найти владельца вечеринки при условии, что он знает всех и его также все знают -
    //не получится, т.к. люди имеют взаимное знакомство
}
