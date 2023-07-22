package apass.estudos.alura.trainingandroid.project03ceep.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.model.Note;

public final class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesViewHolder> {

    private final Context context;
    private final List<Note> notes;

    public NotesListAdapter(@NonNull Context context, @NonNull List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTv, descriptionTv;

        NotesViewHolder(View view) {
            super(view);
            titleTv = view.findViewById(R.id.item_note_title_tv);
            descriptionTv = view.findViewById(R.id.item_note_description_tv);
        }

        void bind(@NonNull Note note) {
            titleTv.setText(note.getTitle());
            descriptionTv.setText(note.getDescription());
        }
    }
}
