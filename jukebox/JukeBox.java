import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class JukeBox {
    private int capacity;
    private LinkedQueue<Song> songQueue;

    public JukeBox(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
        this.songQueue = new LinkedQueue<>();
    }

    public void addSongToQueue(Song song) {
        if (songQueue.size() == capacity) {
            throw new IllegalStateException("Queue is at maximum capacity");
        }
        else if (songQueue.contains(song)) {
            throw new IllegalArgumentException("Song already exists in queue");
        }
        
        songQueue.enqueue((song));
    }
    
    public void addAlbumToQueue(Album album) {
        while (album.size() > 0 && songQueue.size() < capacity) {
            Song song = album.removeSong();
            song.setAlbum(album);
            songQueue.enqueue(song);
        }
    }

    public Song playSong() {
        if (songQueue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Song s = songQueue.dequeue();
        return s;
    }

    public void shuffleSongQueue() {
        ArrayList<Song> elements = songQueue.getList();

        Collections.shuffle(elements);
        
        songQueue.clear();

        for (Song e : elements) {
            songQueue.enqueue(e);
        }
    }

    public int size() {
        return songQueue.size();
    }

    public int capacity() {
        return capacity;
    }

    public boolean isFull() {
        return songQueue.size() == capacity;
    }

    public boolean isEmpty() {
        return songQueue.size() == 0;
    }

    @Override
    public String toString() {
        String str = "";
        ArrayList<Song> elements = songQueue.getList();
        for (Song e : elements) {
            str += e + " -> ";
        }
        str += "END";
        return str;
    }
}