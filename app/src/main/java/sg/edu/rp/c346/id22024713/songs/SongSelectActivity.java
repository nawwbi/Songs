package sg.edu.rp.c346.id22024713.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SongSelectActivity extends AppCompatActivity {

    EditText etSong, etSinger, etYear;
    RadioGroup rgStars;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_select);

        etSong = findViewById(R.id.editTextText4);
        etSinger = findViewById(R.id.editTextText5);
        etYear = findViewById(R.id.editTextText6);

        rgStars = findViewById(R.id.radioGroup2);

        btnUpdate = findViewById(R.id.button4);
        btnDelete = findViewById(R.id.button5);
        btnCancel = findViewById(R.id.button6);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("list");

        etSong.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(Integer.toString(data.getYear()));;

        btnUpdate.setOnClickListener(v -> {
            String songInput = etSong.getText().toString();
            String singerInput = etSinger.getText().toString();
            String yearString = etYear.getText().toString();
            int yearInput = Integer.parseInt(yearString);

            int checkedRadioId = rgStars.getCheckedRadioButtonId();
            int starsInput = 0;
            if (checkedRadioId == R.id.radioButton6) {
                starsInput = 1;
            } else if (checkedRadioId == R.id.radioButton7) {
                starsInput = 2;
            } else if (checkedRadioId == R.id.radioButton8) {
                starsInput = 3;
            } else if (checkedRadioId == R.id.radioButton9) {
                starsInput = 4;
            } else {
                starsInput = 5;
            }
            DBHelper dbh = new DBHelper(SongSelectActivity.this);
            data.setSongContent(songInput, singerInput, yearInput, starsInput);
            dbh.updateSong(data);
            Toast.makeText(SongSelectActivity.this, "Song successfully updated!", Toast.LENGTH_SHORT).show();
            dbh.close();
            finish();
            Intent intent1 = new Intent(SongSelectActivity.this, SongListActivity.class);
            startActivity(intent1);
        });

        btnDelete.setOnClickListener(v -> {
            DBHelper dbh = new DBHelper(SongSelectActivity.this);
            dbh.deleteSong(data.getId());
            Toast.makeText(SongSelectActivity.this, "Song successfully deleted.", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent2 = new Intent(SongSelectActivity.this, SongListActivity.class);
            startActivity(intent2);
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }
}