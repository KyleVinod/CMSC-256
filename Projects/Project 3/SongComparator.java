package cmsc256;
import bridges.data_src_dependent.Song;

import java.util.Comparator;
//Comparator for sorting the list
public class SongComparator implements Comparator<Song> {
    public int compare(Song name1, Song name2) {
        int compare = name1.getArtist().compareTo(name2.getArtist());
        return compare;
    }
}
