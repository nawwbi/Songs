package sg.edu.rp.c346.id22024713.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    ListView lvSongs;
    Button btnFilter, btnBack;
    CustomAdapter adapter, adapter2;
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
        adapter = new CustomAdapter(SongListActivity.this, R.layout.row, data);
        lvSongs.setAdapter(adapter);

        btnFilter.setOnClickListener(v -> {
            ArrayList<Song> filter = db.getFilteredSongs();
            db.close();
            adapter2 = new CustomAdapter(SongListActivity.this, R.layout.row, filter);
            lvSongs.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();
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