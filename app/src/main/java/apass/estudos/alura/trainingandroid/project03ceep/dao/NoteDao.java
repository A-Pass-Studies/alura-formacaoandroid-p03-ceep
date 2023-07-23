package apass.estudos.alura.trainingandroid.project03ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import apass.estudos.alura.trainingandroid.project03ceep.model.Note;

public class NoteDao {

    private final static ArrayList<Note> notes = new ArrayList<>();

    public List<Note> all() {
        return (List<Note>) notes.clone();
    }

    public void insert(Note... note) {
        NoteDao.notes.addAll(Arrays.asList(note));
    }

    public void altera(int position, Note note) {
        notes.set(position, note);
    }

    public void remove(int position) {
        notes.remove(position);
    }

    public void toggle(int startPosition, int endPosition) {
        Collections.swap(notes, startPosition, endPosition);
    }

    public void removeAll() {
        notes.clear();
    }

    public void mock() {
        for (Integer i = 0; i < 2; i++) {
            insert(new Note("Nota de número " + i, "Descrição" + i + " descrição" + i + " descrição " + i));
        }
    }
}
