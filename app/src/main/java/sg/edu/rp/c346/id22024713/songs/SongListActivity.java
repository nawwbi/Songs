package sg.edu.rp.c346.id22024713.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    ListView lvSongs;
    Button btnFilter, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lvSongs = findViewById(R.id.listView);
        btnFilter = findViewById(R.id.button3);
        btnBack = findViewById(R.id.button7);

        DBHelper db = new DBHelper(SongListActivity.this);
        ArrayList<Song> data = db.getSongs();
        db.close();
        ArrayAdapter aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lvSongs.setAdapter(aaSongs);

        btnFilter.setOnClickListener(v -> {
            ArrayList<Song> filter = db.getFilteredSongs();
            db.close();
            ArrayAdapter aaFilter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filter);
            lvSongs.setAdapter(aaFilter);
            aaFilter.notifyDataSetChanged();
        });

        lvSongs.setOnItemClickListener((parent, view, position, id) -> {

            Song list = data.get(position);
            Intent intent = new Intent(SongListActivity.this, SongSelectActivity.class);
            intent.putExtra("list", list);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(SongListActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}