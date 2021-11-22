package org.itstep;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
Набрать com.google.guava для поиска библиотеки в maven репозитории
 */
public class Vocabulary {
    public static void main(String[] args) {
//Количество букв английского алфавита
        int count = 'z' - 'a' + 1;
        System.out.println("Количество букв английского алфавита " + count);

        //Создать словарь
        List<String> vocabulary = new LinkedList<>();

        //Заполнить словарь из файла
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("src/org/itstep/data/dictionary.txt"))) {
            String s;
            do {
                s = br.readLine();
                if (s != null)
                    vocabulary.add(s);
            }
            while (s != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Размер словаря: " + vocabulary.size());

        /*
        Collections.sort(vocabulary);
        for (String s:vocabulary)
            System.out.println(s);
         */

        //1. Вывести первые 10 слов
        ListIterator<String> it = vocabulary.listIterator(0);
        int i = 0;
        while (it.hasNext() && i < 10) {
            System.out.print(it.next() + " ");
            i++;
        }
        System.out.println();

        //2. Вывести последние 10 слов
        it = vocabulary.listIterator(vocabulary.size());
        i = 0;
        while (it.hasPrevious() && i < 10) {
            System.out.print(it.previous() + " ");
            i++;
        }
        System.out.println();

        //3. Сколько слов однобуквенных, двухбуквенных и т.д.
        //Сколько букв в самом длинном слове
        int maxLenght = 0;
        it = vocabulary.listIterator(0);
        String s;
        while (it.hasNext()) {
            s = it.next();
            if (s.length() > maxLenght)
                maxLenght = s.length();
        }
        System.out.println("Максимальное количество букв: " + maxLenght);

        int[] hist = new int[maxLenght];
        it = vocabulary.listIterator(0);
        while (it.hasNext()) {
            s = it.next();
            hist[s.length() - 1]++;
        }

        for (i = 0; i < maxLenght; i++)
            System.out.printf("Букв: %d, слов: %d%n", i + 1, hist[i]);

        //4. Вывести все слова - палиндромы
        it = vocabulary.listIterator(0);
        while (it.hasNext()) {
            s = it.next();
            if (s.equals(new StringBuilder(s).reverse().toString()))
                System.out.print(s + " ");
        }
        System.out.println();

        //5. Вывести все слова - анаграммы
        Multimap<String, String> anagrams = ArrayListMultimap.create();
        it = vocabulary.listIterator(0);
        while (it.hasNext()) {
            s = it.next();
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            anagrams.put(key, s);
        }
        System.out.println(anagrams.size());

        System.out.println(anagrams.keySet().size());
        String key = "", key2 = "";
        Map.Entry<String, String> entry2 = null;
        for (Map.Entry<String, String> entry : anagrams.entries()) {
            key = entry.getKey();
            if (key.equals(key2)) {
                System.out.print(entry2.getValue() + " " + entry.getValue()+", ");
            }
            key2 = key;
            entry2 = entry;
        }
        System.out.println();

        //6. Найти слова с тремя одинаковыми буквами
        it = vocabulary.listIterator(0);
        while (it.hasNext()) {
            s = it.next();
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            count = 1;
            char c = ' ';
            for (i = 0; i < arr.length; i++) {
                char c2 = arr[i];
                if (c == c2) count++;
                else count = 1;
                if (count == 3) { //Проверить с 4-мя буквами
                    System.out.print(s+" ");
                    break;
                }
                c = c2;
            }
        }
        System.out.println();

        //7. Найти слова, где 3 буквы следуют в алфавитном порядке
        it = vocabulary.listIterator(0);
        while (it.hasNext()) {
            s = it.next();
            char[] arr = s.toCharArray();
            count = 1;
            char c = ' ';
            for (i = 0; i < arr.length; i++) {
                char c2 = arr[i];
                if (c2-c==1) count++;
                else count = 1;
                if (count == 3) { //Проверить с 4-мя буквами
                    System.out.print(s+" ");
                    break;
                }
                c = c2;
            }
        }
        System.out.println();
    }
}
