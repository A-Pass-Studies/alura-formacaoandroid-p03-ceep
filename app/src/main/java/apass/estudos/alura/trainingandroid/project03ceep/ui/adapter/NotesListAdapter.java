package apass.estudos.alura.trainingandroid.project03ceep.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.model.Note;

public class NotesListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Note> notes;

    public NotesListAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View createdView = LayoutInflater.from(context).inflate(R.layout.item_note, viewGroup, false);
        Note nota = notes.get(position);

        TextView title = createdView.findViewById(R.id.item_note_title_tv);
        title.setText(nota.getTitle());

        TextView description = createdView.findViewById(R.id.item_note_description_tv);
        description.setText(nota.getDescription());

        return createdView;
    }
}
