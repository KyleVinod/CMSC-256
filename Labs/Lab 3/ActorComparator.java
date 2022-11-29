package cmsc256;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.Comparator;

public class ActorComparator implements Comparator<ActorMovieIMDB> {
    public int compare(ActorMovieIMDB name1, ActorMovieIMDB name2) {
        int compare = name1.getActor().compareTo(name2.getActor());
        return compare;
    }
}
