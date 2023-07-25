package apass.estudos.alura.trainingandroid.project03ceep.ui.note.list;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.BiConsumer;
import java.util.function.IntConsumer;


public class NoteItemTouchCallback extends ItemTouchHelper.Callback {

    private final IntConsumer onSwipedListener;

    private final BiConsumer<Integer, Integer> onMoveListener;

    NoteItemTouchCallback(IntConsumer onSwipedListener, BiConsumer<Integer, Integer> onMoveListener) {
        this.onSwipedListener = onSwipedListener;
        this.onMoveListener = onMoveListener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int swipeFlags = ItemTouchHelper.END ;
        final int moveFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(moveFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        onMoveListener.accept(viewHolder.getAbsoluteAdapterPosition(), target.getAbsoluteAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        onSwipedListener.accept(viewHolder.getAbsoluteAdapterPosition());
    }
}
