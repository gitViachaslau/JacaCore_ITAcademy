import comparators.ProcessorFrequencyComparator;
import processors.Manufacturer;
import processors.Processor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Processor> list = new ArrayList();
        list.add(new Processor(Manufacturer.INTEL, "Core i9-9900KS", 5000));
        list.add(new Processor(Manufacturer.INTEL, "Xeon E-2136", 4500));
        list.add(new Processor(Manufacturer.INTEL, "Core i5-9600K", 4600));
        list.add(new Processor(Manufacturer.INTEL, "Core i5-9500", 4400));
        list.add(new Processor(Manufacturer.INTEL, "Core i5-9500TE", 3600));
        list.add(new Processor(Manufacturer.AMD, "EPYC 7532", 3300));
        list.add(new Processor(Manufacturer.BAIKAL, "T1", 1200));
        list.add(new Processor(Manufacturer.AMD, "EPYC 7542", 3400));
        list.add(new Processor(Manufacturer.AMD, "Ryzen Threadripper 3990X", 4300));
        list.add(new Processor(Manufacturer.INTEL, "Celeron G4930T", 3000));

        System.out.println("Сортировка по частоте: ");
        list.stream()
                .sorted(new ProcessorFrequencyComparator())
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Вывод частот выше 3500 пропуская первых 2 и пропуская последнх 2:");
        list.stream()
                .skip(2)
                .limit(6)
                .map(Processor::getFrequency)
                .filter(x -> x > 3500)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Вывод процессоров с частотой увеличенной на 100:");
        list.stream()
                .peek(x -> x.setFrequency(x.getFrequency() + 100))
                .forEach(System.out::println);

    }
}
