package apass.estudos.alura.trainingandroid.project03ceep.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.model.Note;

public final class CreateNoteActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_KEY_NEW_NOTE = "NEW_NOTE";

    public static final Integer REQUEST_CODE_NEW_NOTE = 0x100;
    public static final Integer RESULT_CODE_NEW_NOTE_SUCCESS = 0x200;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.actionbar_title_create_note));

        setContentView(R.layout.activity_create_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_create_note_done) {
            done();
        }
        return super.onOptionsItemSelected(item);
    }

    private void done() {
        final Note note = createNewNote();
        returnNewNote(note);
    }

    private void returnNewNote(Note note) {
        setResult(RESULT_CODE_NEW_NOTE_SUCCESS, new Intent().putExtra(INTENT_EXTRA_KEY_NEW_NOTE, note));
        finish();
    }

    @NonNull
    private Note createNewNote() {
        EditText title = findViewById(R.id.activity_create_note_title_et), description = findViewById(R.id.activity_create_note_description_et);

        return new Note(title.getText().toString(), description.getText().toString());
    }
}
