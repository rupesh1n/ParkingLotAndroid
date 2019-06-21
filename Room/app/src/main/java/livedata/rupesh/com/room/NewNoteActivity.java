package livedata.rupesh.com.room;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "note_added";
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        Button button = findViewById(R.id.button2);
        editText = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                if(TextUtils.isEmpty(editText.getText())){
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String note = editText.getText().toString();
                    resultIntent.putExtra(NOTE_ADDED, note);
                    setResult(RESULT_OK,resultIntent);
                }
                finish();
            }
        });
    }

}
