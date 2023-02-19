package comparators;

import processors.Processor;

import java.util.Comparator;

public class ProcessorFrequencyComparator implements Comparator <Processor> {

    @Override
    public int compare(Processor o1, Processor o2) {
        return Integer.compare(o1.getFrequency(), o2.getFrequency());
    }
}
