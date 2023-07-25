package apass.estudos.alura.trainingandroid.project03ceep.ui.note.list;

import static apass.estudos.alura.trainingandroid.project03ceep.ui.note.NoteFormActivity.INTENT_EXTRA_KEY_NOTE;
import static apass.estudos.alura.trainingandroid.project03ceep.ui.note.NoteFormActivity.REQUEST_CODE_EDIT_NOTE;
import static apass.estudos.alura.trainingandroid.project03ceep.ui.note.NoteFormActivity.REQUEST_CODE_NEW_NOTE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.dao.NoteDao;
import apass.estudos.alura.trainingandroid.project03ceep.model.Note;
import apass.estudos.alura.trainingandroid.project03ceep.ui.note.NoteFormActivity;
import apass.estudos.alura.trainingandroid.project03ceep.ui.note.NoteVO;

public final class NotesListActivity extends AppCompatActivity {

    @NotNull
    private final NoteDao noteDao = new NoteDao();
    @NonNull
    private final NotesListAdapter notesAdapter;

    public NotesListActivity() {
        super();
        noteDao.mock();
        notesAdapter = new NotesListAdapter(this, noteDao.all(), this::onNoteClick);
    }

    private void onNoteClick(final Note note, final int position) {
        Intent intent = new Intent(this, NoteFormActivity.class).putExtra(INTENT_EXTRA_KEY_NOTE, new NoteVO(note, position));
        startActivityForResult(intent, REQUEST_CODE_EDIT_NOTE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.actionbar_title_note_list));
        setContentView(R.layout.activity_note_list);
        configureRecyclerView();
        configureInsertNoteAction();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null && data.hasExtra(INTENT_EXTRA_KEY_NOTE)) {
            final NoteVO noteVo = data.getParcelableExtra(INTENT_EXTRA_KEY_NOTE);
            if (noteVo != null) {
                if (resultCode == RESULT_OK) {
                    switch (requestCode) {
                        case REQUEST_CODE_NEW_NOTE:
                            insertNote(noteVo);
                            break;
                        case REQUEST_CODE_EDIT_NOTE:
                            updateNote(noteVo);
                            break;
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertNote(@NonNull final NoteVO noteVo) {
        noteDao.insert(noteVo.getNote());
        notesAdapter.insert(noteVo.getNote());
        Toast.makeText(this, R.string.create_note_save_result, Toast.LENGTH_SHORT).show();
    }

    private void updateNote(@NonNull final NoteVO noteVo) {
        if(noteVo.isValidPosition()) {
            noteDao.update(noteVo.getPosition(), noteVo.getNote());
            notesAdapter.update(noteVo.getPosition(), noteVo.getNote());
            Toast.makeText(this, R.string.edit_note_save_result, Toast.LENGTH_SHORT).show();
        }
    }

    private void removeNote(final int position) {
        noteDao.remove(position);
        notesAdapter.remove(position);
    }

    public void configureRecyclerView() {
        final RecyclerView notesRv = findViewById(R.id.activity_note_list_rv);
        notesRv.setAdapter(notesAdapter);
        new ItemTouchHelper(new NoteItemTouchCallback(this::removeNote)).attachToRecyclerView(notesRv);
    }

    private void configureInsertNoteAction() {
        findViewById(R.id.activity_note_list_insert_note_tv).setOnClickListener(v -> {
            final Intent intent = new Intent(NotesListActivity.this, NoteFormActivity.class);
            startActivityForResult(intent, REQUEST_CODE_NEW_NOTE);
        });
    }
}
