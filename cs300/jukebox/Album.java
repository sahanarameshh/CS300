import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Album {
    private String albumName;
    private int size;
    private LinkedStack<Song> trackList;

    public Album(String albumName) {
        this.albumName = albumName;
        this.size = 0;
        this.trackList = new LinkedStack<Song>();
    }

    public void addSong(Song s) {
        if (trackList.contains(s)) {
            throw new IllegalArgumentException("Song already exists in album");
        }

        trackList.push(s);
        size++;
    }

    public Song removeSong() {
        if (trackList.isEmpty()) {
            throw new NoSuchElementException("Album is empty");
        }
        Song s = trackList.pop();
        size--;
        return s;
    }

    public Song firstSong() {
        return trackList.peek();
    }

    public String getAlbumName() {
        return albumName;
    }
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String str = albumName;
        ArrayList<Song> s = trackList.getList();
        for (Song element : s) {
            str += "\n";
            str += element.toString();
        }
        return str;
    }
}
