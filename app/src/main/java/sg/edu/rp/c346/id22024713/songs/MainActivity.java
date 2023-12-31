package sg.edu.rp.c346.id22024713.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etSong, etSinger, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSong = findViewById(R.id.editTextText);
        etSinger = findViewById(R.id.editTextText2);
        etYear = findViewById(R.id.editTextText3);

        rgStars = findViewById(R.id.radioGroup);

        btnInsert = findViewById(R.id.button);
        btnShow = findViewById(R.id.button2);

        btnInsert.setOnClickListener(v -> {
            if (isEmpty() == false) {
                String songInput = etSong.getText().toString();
                String singerInput = etSinger.getText().toString();
                String yearString = etYear.getText().toString();
                int yearInput = Integer.parseInt(yearString);

                int checkedRadioId = rgStars.getCheckedRadioButtonId();
                int starsInput = 0;
                if (checkedRadioId == R.id.radioButton) {
                    starsInput = 1;
                } else if (checkedRadioId == R.id.radioButton2) {
                    starsInput = 2;
                } else if (checkedRadioId == R.id.radioButton3) {
                    starsInput = 3;
                } else if (checkedRadioId == R.id.radioButton4) {
                    starsInput = 4;
                } else {
                    starsInput = 5;
                }

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertSong(songInput, singerInput, yearInput, starsInput);
                Toast.makeText(MainActivity.this, "Song successfully added!", Toast.LENGTH_SHORT).show();
                etSong.setText(null);
                etSinger.setText(null);
                etYear.setText(null);
                rgStars.clearCheck();
            } else {
                Toast.makeText(MainActivity.this, "Enter empty field.", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SongListActivity.class);
            startActivity(intent);
        });
    }

    private boolean isEmpty() {
        if (etSong.getText().toString().isEmpty()) {
            etSong.setError("Song field is empty.");
            return true;
        } else if (etSinger.getText().toString().isEmpty()) {
            etSinger.setError("Singer field is empty.");
            return true;
        } else if (etYear.getText().toString().isEmpty()) {
            etYear.setError("Year field is empty.");
            return true;
        }
        return false;
    }
}