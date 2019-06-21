package livedata.rupesh.com.room;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private  NoteRoomDatabase noteRoomDatabase;
    private NoteDao noteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRoomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = noteRoomDatabase.noteDao();
        mAllNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }
    public LiveData<List<Note>> getmAllNotes(){
        return mAllNotes;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    Log.i(TAG, "ViewModel Destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao mNoteDao;

        public InsertAsyncTask(NoteDao mNoteDao){
            this.mNoteDao = mNoteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
}
