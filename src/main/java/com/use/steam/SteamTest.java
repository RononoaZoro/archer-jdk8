package com.use.steam;

import com.use.entity.Transaction;
import com.use.entity.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: jdk8 Steam  <br>
 * @Date: 2019/10/31 20:51 <br>
 * @Author: luoxz <br>
 * @Version: 1.0 <br>
 */
public class SteamTest {
    public static void main(String[] args) {

//                //排序
//                sort();
//
//                //求两个集合的交集，并集，差集，去重并集
//                operateCollections();
//
//                //list to map
//                list2Map();

        new ArrayList<>().stream().forEach(System.out::println);

    }

    private static void list2Map() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "luo"));
        userList.add(new User(2, "xu"));
        userList.add(new User(3, "zheng"));

        //1、指定key-value，value是对象中的某个属性值
        Map<Integer, String> userMap1 = userList.stream().collect(Collectors.toMap(User::getId, User::getName));

        //2、指定key-value，value是对象本身，User->User 是一个返回本身的lambda表达式
        Map<Integer, User> userMap2 = userList.stream().collect(Collectors.toMap(User::getId, user -> user));

        //3、指定key-value，value是对象本身，Function.identity()是简洁写法，也是返回对象本身
        Map<Integer, User> userMap3 = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));

        //4、指定key-value，value是对象本身，Function.identity()是简洁写法，也是返回对象本身，key 冲突的解决办法，这里选择第二个key覆盖第一个key。
        Map<Integer, User> userMap4 = userList.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key2));
    }

    public static void sort() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(6, 60, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(2, 20, Transaction.Type.A));
        transactions.add(new Transaction(4, 40, Transaction.Type.C));

        // JDK 8 如果发现type为grocery的所有交易, 然后返回以交易值降序排序的交易ID集合
        List<Integer> transactionsIds =
            transactions.parallelStream().filter(t -> t.getType() == Transaction.Type.GEOCERY)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(Collectors.toList());
        System.out.println(transactionsIds);//[6, 5, 3, 1]
    }

    public static void operateCollections() {
        List<String> list1 = new ArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List<String> list2 = new ArrayList();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println("---得到交集 intersection---");
        intersection.parallelStream().forEach(System.out::println);

        //同双冒号写法
        intersection.parallelStream().forEach(x -> {
            System.out.println("x =" + x);
        });

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
        System.out.println("---得到差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
        System.out.println("---得到差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out::println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(Collectors.toList());
        List<String> listAll2 = list2.parallelStream().collect(Collectors.toList());
        listAll.addAll(listAll2);
        System.out.println("---得到并集 listAll---");
        listAll.parallelStream().forEach(System.out::println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(Collectors.toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEach(System.out::println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEach(System.out::println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEach(System.out::println);
    }

}
