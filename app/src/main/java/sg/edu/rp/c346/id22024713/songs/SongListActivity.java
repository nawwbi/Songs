package sg.edu.rp.c346.id22024713.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    ListView lvSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lvSongs = findViewById(R.id.listView);

        DBHelper db = new DBHelper(SongListActivity.this);
        ArrayList<Song> data = db.getSongs();
        db.close();
        ArrayAdapter aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lvSongs.setAdapter(aaSongs);
    }
}