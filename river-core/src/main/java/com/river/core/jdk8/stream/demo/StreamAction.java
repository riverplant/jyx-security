package com.river.core.jdk8.stream.demo;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAction {
	public static void main(String[] args) {
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		Trader raoul = new Trader("Raoul", "Cambridge");

		List<Transaction> transctions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));
		// 1.获取所有2011年的交易，然后按照交易值排序
		List<Transaction> transactions = transctions.stream().filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

		// 2.根据去重获得交易城市Stream.map(Function<? super Transaction, ? extends String>
		// mapper)
		transctions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);

		// 3.查找到剑桥的所有交易然后按照名字排序
		transctions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equalsIgnoreCase("Cambridge"))
				.distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());

		// 4.返回所有traders名字的一个String,按照字母排序
		String value = transctions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("",
				(name1, name2) -> name1 + name2);

		// 5.查出在米兰的traders
		transctions.stream().map(t -> t.getTrader()).filter(t -> t.getCity().equalsIgnoreCase("Milan"))
				.forEach(System.out::println);

		transctions.stream().anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("Milan"));

		// 6.打印出所有在剑桥的traders 进行transction的值
		transctions.stream().filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge")).map(t -> t.getValue())
				.forEach(System.out::println);

		// 7.找出所有transactions里面的最大值
		int max = transctions.stream().map(Transaction::getValue).mapToInt(t -> t.intValue()).max().orElse(-1);
		System.out.println(max);
		transctions.stream().map(Transaction::getValue).reduce((i, j) -> i > j ? i : j).get();
		transctions.stream().map(Transaction::getValue).reduce(Integer::max).get();
		// 8.找到最小值的transaction
		// transctions.stream().filter(a->a.getValue()==max);
		transctions.stream().reduce((i, j) -> i.getValue() < j.getValue() ? i : j).get();

	}

}
