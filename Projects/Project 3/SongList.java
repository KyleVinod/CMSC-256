/**
 * SongList
 * Kyle Vinod
 * Project 3
 * CMSC256 - 001
 * Spring Semester
 * This project I have to gather data from a bridge API
 * and have it go through a constructor. This will then
 * go through different methods, and then will display
 * the artists, their songs, and their albums if in the API.
 */
package cmsc256;
import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;
import java.util.*;
public class SongList implements cmsc256.List<bridges.data_src_dependent.Song>{
    //Instances Variables
    private SLelement<Song> head;
    private SLelement<Song> tail;
    private SLelement<Song> curr;
    private int listSize;

    //Default Constructor
    public SongList() {
        clear();
        helper();
    }
    //To clear the list and current
    @Override
    public void clear() {
        //Setting the default and clear values
        curr = tail = new SLelement<>(null);
        head = new SLelement<>(tail);
        listSize = 0;
    }
    //To insert a song where current is without changing the other song
    @Override
    public boolean insert(bridges.data_src_dependent.Song it) {
        //Setting the next value after this insert
        curr.setNext(new SLelement<>(curr.getValue(), curr.getNext()));
        //Setting it variable in
        curr.setValue(it);
        //New tail
        if (tail == curr) {
            tail = curr.getNext();
        }
        listSize++;
        return true;
    }
    //Adding a song to the end of the list
    @Override
    public boolean append(bridges.data_src_dependent.Song it) {
        //Setting up to make it the new tail
        tail.setNext(new SLelement<>(null));
        tail.setValue(it);
        tail = tail.getNext();
        listSize++;
        return true;
    }
    //Removing the song in current and returning it
    @Override
    public bridges.data_src_dependent.Song remove() {
        //Won't remove if at end
        if (curr == tail) {
            return null;
        }
        //Remembering element to remove
        Song it = curr.getValue();
        //Setting current value to next value
        curr.setValue(curr.getNext().getValue());
        if (curr.getNext() == tail) {
            tail = curr;
        }
        curr.setNext(curr.getNext().getNext());
        listSize--;
        return it;
    }
    //Current is set to the beginning
    @Override
    public void moveToStart() {
        curr = head.getNext();
    }
    //Current is set to the end
    @Override
    public void moveToEnd() {
        curr = tail;
    }
    //Current goes back by one song
    @Override
    public void prev() {
        //Won't work if at beginning
        if (head.getNext() == curr) {
            return;
        }
        //Making temp go before curr element
        SLelement<Song> temp = head;
        while (temp.getNext() != curr) {
            temp = temp.getNext();
        }
        curr = temp;
    }
    //Current goes one song forward
    @Override
    public void next() {
        //Won't work if curr is at end
        if (curr != tail) {
            curr = curr.getNext();
        }
    }
    //Gets the length of the list
    @Override
    public int length() {
        return listSize;
    }
    //Shows what song is in the current
    @Override
    public int currPos() {
        //Counts how long it takes for temp to get to curr
        SLelement<Song> temp = head.getNext();
        int i;
        for (i = 0; curr != temp; i++) {
            temp = temp.getNext();
        }
        return i;
    }
    //To move current to a certain position in the list
    @Override
    public boolean moveToPos(int pos) {
        //Won't work if pos is less than 0 or greater than the size of the list
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }
        //Counting how long it will take to get to position and setting curr there
        curr = head.getNext();
        for (int i = 0; i < pos; i++) {
            curr = curr.getNext();
        }
        return true;
    }
    //Determines if current is at the end
    @Override
    public boolean isAtEnd() {
        if (curr == tail) {
            return true;
        }
        else {
            return false;
        }
    }
    //Sees what song current is on
    @Override
    public bridges.data_src_dependent.Song getValue() {
        if (curr == tail) {
            return null;
        }
        return curr.getValue();
    }
    //Gets the input by teh user for an artist, and determines if artist is in list.
    //If artist is not in list, it will return a no artist string, otherwise it will show all songs and albums by artist
    public String getSongsByArtist(String artist) {
        //New list to insert data from linked list
        LinkedList<Song> newList = new LinkedList();
        String songs = "";
        moveToStart();
        //New list will get data only containing info from the artist
        for (int i = 0; i < listSize; i++) {
                newList.add(curr.getValue());
            next();
        }
        //Puts list in alphabetical order
        SongComparator artistList = new SongComparator();
        newList.sort(artistList);
        //If nothing is in newList it won't work, else it will display every song and album by the artist
        for (int i = 0; i < newList.size(); i++) {
            moveToStart();
            Song base = curr.getValue();
            if (base.getArtist().equals(artist)) {
                songs = "Title: " + base.getSongTitle() + " Artist: " + base.getArtist() + " Album: " + base.getAlbumTitle();
            }
            next();
        }
        if (songs.equals("")) {
            songs = "There are no songs by " + artist + " in this playlist.";
        }
        return songs;
    }
    //Method that moves all data from bridge API into constructor
    private void helper() {
        //Bridge logged in and getting info
        Bridges bridges = new Bridges(3, "KyleVinod", "1261544500851");
        DataSource ds = bridges.getDataSource();
        java.util.List<Song> songsData = null;
        //Loading data into List to append into constructor
        try {
            songsData = ds.getSongData();
        }
        catch (Exception e) {
            System.out.println("Unable to connect to Bridges.");
        }
        for (int i = 0; i < songsData.size(); i++) {
            append(songsData.get(i));
        }
    }
}
