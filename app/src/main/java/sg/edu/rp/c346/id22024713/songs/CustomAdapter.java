package sg.edu.rp.c346.id22024713.songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;
    TextView tvSong, tvYear, tvStars, tvSingers;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        tvSong = rowView.findViewById(R.id.textViewSong);
        tvYear = rowView.findViewById(R.id.textViewYear);
        tvStars = rowView.findViewById(R.id.textViewStars);
        tvSingers = rowView.findViewById(R.id.textViewSingers);

        Song currentSong = songList.get(position);

        tvSong.setText(currentSong.getTitle());
        tvYear.setText(Integer.toString(currentSong.getYear()));
        tvStars.setText("     " + "* ".repeat(currentSong.getStars()));
        tvSingers.setText(currentSong.getSingers());

        return rowView;

    }
}
