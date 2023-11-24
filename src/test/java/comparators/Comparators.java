package comparators;

import java.util.Comparator;

public interface Comparators {
    Comparator<String> ascComparator = (o1, o2) -> {
        double d1 = Double.parseDouble(o1.substring(o1.lastIndexOf('$') +1));
        double d2 = Double.parseDouble(o2.substring(o2.lastIndexOf('$') +1));
        return Double.compare(d1, d2);
    };
    Comparator<String> descComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            double d1 = Double.parseDouble(o1.substring(o1.lastIndexOf('$') +1));
            double d2 = Double.parseDouble(o2.substring(o2.lastIndexOf('$') +1));
            return Double.compare(d2, d1);
        }
    };
}
