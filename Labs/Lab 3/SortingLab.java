package cmsc256;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingLab {
    public static void main(String [] args) {
        Bridges bridges = new Bridges(3, "KyleVinod", "1261544500851");
        DataSource ds = bridges.getDataSource();
        List<ActorMovieIMDB> movieData = null;
        try {
            movieData = ds.getActorMovieIMDBData(Integer.MAX_VALUE);
        }
        catch (Exception e) {
            System.out.println("Unable to connect to Bridges.");
        }
        for(int i = 0; i < 5; i++) {
            ActorMovieIMDB entry = movieData.get(i);
            System.out.println("" + i + ".  " + entry.getActor() + " was in " + entry.getMovie());
        }
        ArrayList<ActorMovieIMDB> filteredMovieList = new ArrayList<>();
        for (ActorMovieIMDB list: movieData
             ) {
            if (list.getMovie().equals("Being_John_Malkovich_(1999)")) {
                System.out.println(list.getActor());
                filteredMovieList.add(list);
            }
        }
        ActorComparator newList = new ActorComparator();
        filteredMovieList.sort(newList);
        for (int i = 0; i < filteredMovieList.size(); i++) {
            System.out.println(filteredMovieList.get(i).getActor());
        }
    }
}
