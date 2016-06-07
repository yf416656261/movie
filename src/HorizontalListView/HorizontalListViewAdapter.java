package HorizontalListView;

import java.util.List;

import com.example.movie.Movie;
import com.example.movie.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class HorizontalListViewAdapter extends BaseAdapter{

	private List<Movie> movieList;
	private Context context;
	private LayoutInflater layoutInflater;
	public class HorizontalListView {
		public ImageView image;
	}
	public HorizontalListViewAdapter(Context context, List<Movie> objects) {
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		movieList = objects;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movieList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HorizontalListView HListView = null;
		if (convertView == null) {
			HListView = new HorizontalListView();
			convertView = layoutInflater.inflate(R.layout.listview_horizontal, null);
			HListView.image = (ImageView)convertView.findViewById(R.id.horizontal);
			convertView.setTag(HListView);
		} else {
			HListView = (HorizontalListView)convertView.getTag();
		}
		String im = (String)movieList.get(position).getImageID();
		int id = context.getResources().getIdentifier(im, "drawable", context.getPackageName());
		HListView.image.setImageDrawable(context.getResources().getDrawable(id));
		return convertView;
	}

}