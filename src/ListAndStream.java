import java.util.Comparator; // импорт пакета функционального интерфейса Comparator<T>
import java.util.List; // импорт интерфейса List
import java.util.function.BiConsumer; //импорт интерфейса BiConsumer из пакета готовых функциональных интерфейсов
import java.util.stream.Collectors; //импорт класса Collectors из пакета stream
import java.util.stream.Stream; // импорт класса Stream из пакета stream

public class ListAndStream {
    public static void main(String[] args) {

        List<Integer> list = List.of(2, 6, 1, 9, 4, 3, 5, 7, 3, 9, 2, 7, 3, 2, 6, 8, 5); // в переменную "list"
        // (интерфейсного типа) записываем ссылку на созданный объект списка с произвольным набором элементов - целых чисел

        System.out.println("Дан список целых чисел:");
        for (Integer e : list) {
            System.out.print(e + ", ");
        }
        System.out.println();
        System.out.println("Определение наименьшего и наибольшего числа в списке:");

        Stream<Integer> stream = list.stream(); // методом ".stream" (из интерфейса Collection<E>) создаем  последовательный
        // поток (по имени "stream") состоящий из элементов списка "list"

        //Вызываем метод findMinMax, в качестве параметров передаем поток, указание о порядке и лямбдо-выражение
        findMinMax(stream,
                Comparator.naturalOrder(),
                (x, y) -> {
                    System.out.println("Наименьшее число: " + x);
                    System.out.println("Наибольшее число: " + y);
                });


        System.out.println("Вывод четных чисел из списка: ");
        searchForEvenNumbers(list); //метод для поиска четных чисел в списке

    }

    public static <T> void findMinMax(Stream<? extends T> stream, // параметр с типом stream
                                      Comparator<? super T> order, // параметр с типом order
                                      BiConsumer<? super T, ? super T> minMaxConsumer) { // параметр minMaxConsumer
        List<T> newList = stream.collect(Collectors.toList()); //с помощью метода из класса Collectors собираем элементы из потока в новый список

        if (!newList.isEmpty()) {
            minMaxConsumer.accept(newList.stream().min(order).get(), newList.stream().max(order).get());// определение min (x) и max(y)
        } else {
            minMaxConsumer.accept(null, null);
        }
    }

    public static void searchForEvenNumbers(List<Integer> list) {
        int n = (int) list.stream().filter(e -> e % 2 == 0).peek(System.out::println).count();
    }

}