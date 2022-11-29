package cmsc256;

import bridges.data_src_dependent.EarthquakeUSGS;

import java.util.Comparator;

public class GraphComparator implements Comparator<EarthquakeUSGS> {
    public int compare(EarthquakeUSGS o1, EarthquakeUSGS o2) {
        return Double.compare(o2.getMagnitude(), o1.getMagnitude());
    }
}
