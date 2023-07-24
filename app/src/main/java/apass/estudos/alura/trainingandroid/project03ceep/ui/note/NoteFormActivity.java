package apass.estudos.alura.trainingandroid.project03ceep.ui.note;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.model.Note;

public final class NoteFormActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_KEY_NOTE = "NOTE";

    public static final int REQUEST_CODE_NEW_NOTE = 0x100;
    public static final int REQUEST_CODE_EDIT_NOTE = 0x101;
    private EditText titleEt;
    private EditText descriptionEt;
    @Nullable
    private NoteVO receivedNoteVO;

    private void onCreateBindViews() {
        titleEt = findViewById(R.id.activity_note_form_title_et);
        descriptionEt = findViewById(R.id.activity_note_form_description_et);
    }

    private void onCreateHandleIntentExtras() {
        if (getIntent().hasExtra(INTENT_EXTRA_KEY_NOTE)) {
            Optional.of((NoteVO) getIntent().getParcelableExtra(INTENT_EXTRA_KEY_NOTE)).ifPresent(noteVo -> {
                receivedNoteVO = noteVo;
                titleEt.setText(receivedNoteVO.getNote().getTitle());
                descriptionEt.setText(receivedNoteVO.getNote().getDescription());
            });
        }
    }

    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.actionbar_title_create_note));

        setContentView(R.layout.activity_note_form);

        onCreateBindViews();

        onCreateHandleIntentExtras();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == R.id.menu_note_form_done) {
            done();
        }
        return super.onOptionsItemSelected(item);
    }

    private void done() {
        returnNote(createNote());
    }

    private void returnNote(final Note note) {
        final NoteVO noteVo = receivedNoteVO == null ? new NoteVO(note) : new NoteVO(note, receivedNoteVO.getPosition());
        setResult(Activity.RESULT_OK, new Intent().putExtra(INTENT_EXTRA_KEY_NOTE, noteVo));
        finish();
    }

    @NonNull
    private Note createNote() {
        return new Note(titleEt.getText().toString(), descriptionEt.getText().toString());
    }
}
