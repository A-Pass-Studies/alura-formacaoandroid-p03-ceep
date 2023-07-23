package apass.estudos.alura.trainingandroid.project03ceep.ui;

import static apass.estudos.alura.trainingandroid.project03ceep.ui.CreateNoteActivity.INTENT_EXTRA_KEY_NEW_NOTE;
import static apass.estudos.alura.trainingandroid.project03ceep.ui.CreateNoteActivity.REQUEST_CODE_NEW_NOTE;
import static apass.estudos.alura.trainingandroid.project03ceep.ui.CreateNoteActivity.RESULT_CODE_NEW_NOTE_SUCCESS;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.dao.NoteDao;
import apass.estudos.alura.trainingandroid.project03ceep.model.Note;
import apass.estudos.alura.trainingandroid.project03ceep.ui.adapter.NotesListAdapter;

public final class NotesListActivity extends AppCompatActivity {

    private final NoteDao noteDao = new NoteDao();
    private final NotesListAdapter notesAdapter;

    public NotesListActivity() {
        super();
        noteDao.mock();
        notesAdapter = new NotesListAdapter(this, noteDao.all());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.actionbar_title_note_list));
        setContentView(R.layout.activity_note_list);
        onCreateConfigureRecyclerView();
        configureInsertNoteAction();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NEW_NOTE && resultCode == RESULT_CODE_NEW_NOTE_SUCCESS && data != null && data.hasExtra(INTENT_EXTRA_KEY_NEW_NOTE)) {
            final Note note = (Note) data.getSerializableExtra(INTENT_EXTRA_KEY_NEW_NOTE);
            if (note != null) {
                noteDao.insert(note);
                notesAdapter.add(note);
                Toast.makeText(this, R.string.create_note_save_result, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onCreateConfigureRecyclerView() {
        final RecyclerView notesLv = findViewById(R.id.activity_note_list_rc);
        notesLv.setAdapter(notesAdapter);
    }

    private void configureInsertNoteAction() {
        findViewById(R.id.activity_note_list_insert_note_tv).setOnClickListener(v -> {
            final Intent intent = new Intent(NotesListActivity.this, CreateNoteActivity.class);
            startActivityForResult(intent, REQUEST_CODE_NEW_NOTE);
        });
    }
}
