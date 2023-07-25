package apass.estudos.alura.trainingandroid.project03ceep.ui.note;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import apass.estudos.alura.trainingandroid.project03ceep.model.Note;

public class NoteVO implements Parcelable {

    private static final int INITIAL_INVALID_POSITION = -1;

    public static final Parcelable.Creator<NoteVO>
            CREATOR = new Parcelable.Creator<NoteVO>() {

        public NoteVO createFromParcel(Parcel in) {
            return new NoteVO(in);
        }

        public NoteVO[] newArray(int size) {
            return new NoteVO[size];
        }
    };
    @NonNull
    private final Note note;
    private final int position;

    public NoteVO(@NonNull Note note) {
        this(note, INITIAL_INVALID_POSITION);
    }

    public NoteVO(@NonNull Note note, int position) {
        this.note = note;
        if(isValidPosition(position)) this.position = position;
        else throw new IllegalArgumentException("The value of the position must be equal to or greater than zero");
    }

    private NoteVO(@NonNull Parcel p) {
        note = new Note(p.readString(), p.readString());
        position = p.readInt();
    }

    private boolean isValidPosition(final int position) {
        return position > -1;
    }

    public boolean isValidPosition() {
        return isValidPosition(position);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(note.getTitle());
        dest.writeString(note.getDescription());
        dest.writeInt(position);
    }

    @NonNull
    public Note getNote() {
        return note;
    }

    public int getPosition() {
        return position;
    }
}
