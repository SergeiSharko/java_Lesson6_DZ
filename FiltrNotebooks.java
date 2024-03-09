// Задание.
// -Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// -Создать множество ноутбуков.
// -Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и
// выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
// “Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет …
// -Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации
// можно также в Map.
// -Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

package Task1_DZ;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class FiltrNotebooks {
    public static void main(String[] args) {
        NoteBook note1 = new NoteBook("Lenovo", 2500, 16, 500, 15.6);
        NoteBook note2 = new NoteBook("Lenovo", 3200, 32, 1000, 16.0);
        NoteBook note3 = new NoteBook("MSI", 2400, 8, 250, 14.0);
        NoteBook note4 = new NoteBook("Acer", 2800, 16, 500, 15.6);
        NoteBook note5 = new NoteBook("Acer", 3000, 32, 750, 16.0);
        NoteBook note6 = new NoteBook("Asus", 3100, 16, 500, 15.6);
        NoteBook note7 = new NoteBook("Asus", 3100, 16, 500, 15.6);
        NoteBook note8 = new NoteBook("Lenovo", 3500, 32, 750, 14.0);

        Set<NoteBook> lapTops = new HashSet<NoteBook>(
                Arrays.asList(note1, note2, note3, note4, note5, note6, note7, note8));

        Map<Integer, String> listCriteria = new TreeMap<Integer, String>();
        listCriteria.put(1, "Производитель");
        listCriteria.put(2, "Такстовая частота процессора");
        listCriteria.put(3, "Объем ОЗУ(Ram) в Гб");
        listCriteria.put(4, "Размер жесткого диска в Гб");
        listCriteria.put(5, "Размер диагонали монитора в дюймах");
        listCriteria.put(6, "Вывод на экран подходящих ноутбуков");
        listCriteria.put(7, "Выход из программы");

        System.out.println("Список всех, имеющихся ноутбуков:");
        for (NoteBook note : lapTops) {
            System.out.println(note);
        }
        System.out.println();

        Map<Integer, String> mapParams = requestFindCriteria(lapTops, listCriteria);       

    }

    public static Map<Integer, String> requestFindCriteria(Set<NoteBook> lapTops, Map<Integer, String> listCriteria) {
        Map<Integer, String> mapParams = new LinkedHashMap<Integer, String>();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Критерии для выбора ноутбука:");

            for (Map.Entry<Integer, String> item : listCriteria.entrySet()) {
                System.out.printf("%d -> %s\n", item.getKey(), item.getValue());
            }

            System.out.print("Выберите пункт меню -> ");

            int choise = scan.nextInt();

            if (choise == 7) {
                break;
            } else if (choise < 1 || choise > 7) {
                System.out.println("Некорректный выбор меню!");
                System.out.println();
            } else if (choise == 6) {
                printNoteBook(lapTops, mapParams);
                mapParams.clear();                             
            } else {
                System.out.print("Введите значения для данного критерия -> ");
                scan.nextLine();
                String paramValue = scan.nextLine();
                mapParams.put(choise, paramValue);                
            }
        }
        scan.close();
        return mapParams;
    }

    public static void printNoteBook(Set<NoteBook> lapTops, Map<Integer, String> mapParams) {
        Set<NoteBook> filtredNote = new HashSet<NoteBook>();

        if (mapParams.isEmpty()) {
            System.out.println();
            System.out.println("Критерии для ноутбука не были указаны. Список всех ноутбуков:");
            for (NoteBook noteBook : lapTops) {
                System.out.println(noteBook);
            }
        } else {
            System.out.println();
            System.out.println("Параметры поиска: " + mapParams + " Список подходящих ноутбуков:");
            filtredNote = searchNoteBook(lapTops, mapParams);
            if (filtredNote.isEmpty()) {
                System.out.println("Нет ноутбуков, удовлетворяющих критериям!");
            }
            for (NoteBook note : filtredNote) {
                System.out.println(note);
            }
        }
    }

    public static Set<NoteBook> searchNoteBook(Set<NoteBook> lapTops, Map<Integer, String> mapParams) {

        Set<NoteBook> filtredNoteBook = new HashSet<NoteBook>(lapTops);

        Iterator<Integer> iter = mapParams.keySet().iterator();

        while (iter.hasNext()) {
            int elem = iter.next();
            Iterator<NoteBook> itNote = filtredNoteBook.iterator();
            switch (elem) {
                case 1:
                    String company = mapParams.get(elem);
                    while (itNote.hasNext()) {
                        var note = itNote.next();
                        if (!note.getCompany().equalsIgnoreCase(company)) {
                            itNote.remove();
                        }
                    }
                    break;
                case 2:
                    int freqCPU = Integer.parseInt(mapParams.get(elem));
                    while (itNote.hasNext()) {
                        var note = itNote.next();
                        if (note.getFreqCPU() < freqCPU) {
                            itNote.remove();
                        }
                    }
                    break;
                case 3:
                    int sizeMemoryGb = Integer.parseInt(mapParams.get(elem));
                    while (itNote.hasNext()) {
                        var note = itNote.next();
                        if (note.getSizeMemory() < sizeMemoryGb) {
                            itNote.remove();
                        }
                    }
                    break;
                case 4:
                    int sizeHDDGb = Integer.parseInt(mapParams.get(elem));
                    while (itNote.hasNext()) {
                        var note = itNote.next();
                        if (note.getSizeHDD() < sizeHDDGb) {
                            itNote.remove();
                        }
                    }
                    break;
                case 5:
                    double sizeMonitor = Double.parseDouble(mapParams.get(elem));
                    while (itNote.hasNext()) {
                        var note = itNote.next();
                        if (note.getSizeMonitor() < sizeMonitor) {
                            itNote.remove();
                        }
                    }
                    break;
            }
        }
        return filtredNoteBook;
    }
}