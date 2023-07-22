package apass.estudos.alura.trainingandroid.project03ceep.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.dao.NoteDao;
import apass.estudos.alura.trainingandroid.project03ceep.ui.adapter.NotesListAdapter;

public class NotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NoteDao noteDao = new NoteDao();
        noteDao.mock();

        setContentView(R.layout.activity_note_list);

        RecyclerView notesLv = findViewById(R.id.activity_note_list_rc);
        notesLv.setAdapter(new NotesListAdapter(this, noteDao.all()));
    }
}
