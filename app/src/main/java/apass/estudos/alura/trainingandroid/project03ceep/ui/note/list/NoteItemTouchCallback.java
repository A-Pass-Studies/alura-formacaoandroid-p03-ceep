package apass.estudos.alura.trainingandroid.project03ceep.ui.note.list;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;


public class NoteItemTouchCallback extends ItemTouchHelper.Callback {

    private final IntConsumer onSwipedListener;

    NoteItemTouchCallback(IntConsumer onSwipedListener) {
        this.onSwipedListener = onSwipedListener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int swipeFlags = ItemTouchHelper.END ;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        onSwipedListener.accept(viewHolder.getAbsoluteAdapterPosition());
    }
}
