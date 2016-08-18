package online.things;

import java.util.ArrayList;

/**
 * Created by jfische1 on 18.08.16.
 */
public class Song {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public ArrayList<Note> getNotes() {
        return notes;
    }

    private ArrayList<Note> notes = new ArrayList<Note>();

    public void addNote(long duration, int frequency) {
        notes.add(new Note(duration, frequency));
    }

}
