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
        if (item.getItemId() == R.id.menu_create_note_save) {
            save();
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        EditText title = findViewById(R.id.activity_create_note_title_et),
                description = findViewById(R.id.activity_create_note_description_et);

        Note note = new Note(title.getText().toString(), description.getText().toString());

        setResult(0X100, new Intent().putExtra(INTENT_EXTRA_KEY_NEW_NOTE, note));
        finish();
    }
}
