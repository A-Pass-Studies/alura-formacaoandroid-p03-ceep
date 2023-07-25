package apass.estudos.alura.trainingandroid.project03ceep.ui.note.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import apass.estudos.alura.trainingandroid.project03ceep.R;
import apass.estudos.alura.trainingandroid.project03ceep.model.Note;

public final class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesViewHolder> {

    private final Context context;
    private final List<Note> notes;

    private final OnNoteClickListener onNoteClickListener;

    public NotesListAdapter(@NonNull Context context, @NonNull List<Note> notes, OnNoteClickListener onNoteClickListener) {
        this.context = context;
        this.notes = notes;
        this.onNoteClickListener = onNoteClickListener;
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

    public void insert(@NotNull final Note note) {
        int position = getItemCount();
        notes.add(note);
        notifyItemInserted(position);
    }

    public void update(final int position, @NotNull final Note note) {
        notes.add(position, note);
        notes.remove(position + 1);
        notifyItemChanged(position);
    }

    public void remove(final int position) {
        notes.remove(position);
        notifyItemRemoved(position);
    }

    public void toggle(final int currentPosition, final int targetPosition) {
        Collections.swap(notes, currentPosition, targetPosition);
        notifyItemMoved(currentPosition, targetPosition);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTv, descriptionTv;

        private Note note;

        NotesViewHolder(final View view) {
            super(view);
            titleTv = view.findViewById(R.id.item_note_title_tv);
            descriptionTv = view.findViewById(R.id.item_note_description_tv);
            view.setOnClickListener(v -> onNoteClickListener.OnNoteClick(note, getAbsoluteAdapterPosition()));
        }

        void bind(@NonNull final Note note) {
            this.note = note;

            titleTv.setText(this.note.getTitle());
            descriptionTv.setText(this.note.getDescription());
        }
    }
}
