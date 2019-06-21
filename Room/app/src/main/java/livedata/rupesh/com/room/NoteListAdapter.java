package livedata.rupesh.com.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context context;
    private List<Note> mNotesList;

    public NoteListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        NoteViewHolder viewHolder =new NoteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (mNotesList != null){
            Note note = mNotesList.get(position);
            holder.setData(note.getNote(), position);
        } else {
            holder.mNoteItemView.setText("No Data");
        }

    }

    @Override
    public int getItemCount() {
        if (mNotesList != null )
            return mNotesList.size();
        else
            return 0;
    }

    public void setNotes(List<Note> notes) {
        mNotesList = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView mNoteItemView;
        private int mPosition;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mNoteItemView = itemView.findViewById(R.id.textView3);
        }

        public void setData(String note, int position) {
            mNoteItemView.setText(position + "    " + note);
            mPosition = position;
        }
    }
}
