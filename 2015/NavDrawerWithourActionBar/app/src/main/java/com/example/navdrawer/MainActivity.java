package com.example.navdrawer;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private String[] mColors;
	private ArrayList<ItemsModel> itemModel;
	private TypedArray itemImages;
	private DrawerAdapter drawerAdapter;
	private int lastIndex=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mColors = getResources().getStringArray(R.array.colors_array);
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList=(ListView)findViewById(R.id.left_drawer);
		itemModel=new ArrayList<ItemsModel>();
		itemImages=getResources().obtainTypedArray(R.array.color_picture);
		View header=getLayoutInflater().inflate(R.layout.header, null);
		ImageView pro=(ImageView)header.findViewById(R.id.profile_image);
		pro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
			}
		});
		
		mDrawerList.addHeaderView(header);
		// set a custom shadow that overlays the main content when the drawer opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
        itemModel.add(new ItemsModel("Black",itemImages.getResourceId(0, -1)));
        itemModel.add(new ItemsModel("Red",itemImages.getResourceId(1, -1)));
        itemModel.add(new ItemsModel("Green",itemImages.getResourceId(2, -1)));
        itemModel.add(new ItemsModel("Blue",itemImages.getResourceId(3, -1)));
        itemModel.add(new ItemsModel("pink",itemImages.getResourceId(4, -1)));
        itemImages.recycle();
        drawerAdapter=new DrawerAdapter(itemModel);
        mDrawerList.setAdapter(drawerAdapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        if (savedInstanceState == null) {
            selectItem(1);
            lastIndex=1;
        }
	}
	public class DrawerItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
			// TODO Auto-generated method stub
			
			if(position != 0){
				lastIndex=position;
				selectItem(position);
			}else{
				mDrawerList.setItemChecked(lastIndex, true);
			}
		}
	}
	private void selectItem(int position){
		Log.d("POS", position+"");
		Fragment fragment=new ColorsFragment();
		Bundle bundle=new Bundle();
		bundle.putInt("Position", position-1);
		fragment.setArguments(bundle);
		FragmentManager fragmentManager=getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		mDrawerList.setItemChecked(position, true);
		setTitle(mColors[position-1]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.action_websearch).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new OnQueryTextListener() { 
            @Override 
            public boolean onQueryTextChange(String query) {
                return true; 
            }
   @Override
   public boolean onQueryTextSubmit(String query) {
    // TODO Auto-generated method stub
    if(query.trim().length() !=0){
        Toast.makeText(getApplicationContext(),"Searching for "+query.toString(), Toast.LENGTH_SHORT).show();
    }
    return false;
   } 
        });
        return super.onCreateOptionsMenu(menu);
    }
	 @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }
	private class DrawerAdapter extends BaseAdapter{
		ArrayList<ItemsModel> itemsmodel;
		public DrawerAdapter(ArrayList<ItemsModel> itemModel) {
			// TODO Auto-generated constructor stub
		this.itemsmodel=itemModel;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return itemsmodel.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return itemsmodel.get(position);
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (view == null) {
	            LayoutInflater mInflater = (LayoutInflater)getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            view = mInflater.inflate(R.layout.list_adapter, null);
	        }
			ImageView img=(ImageView)view.findViewById(R.id.img);
			TextView name=(TextView)view.findViewById(R.id.name);
			name.setText(itemsmodel.get(position).getItemName());
			img.setImageResource(itemsmodel.get(position).getItemImage());
			return view;
		}
	}
	private class ItemsModel{
		private String itemName;
		private int itemImage;
		public ItemsModel(String name, int image) {
			// TODO Auto-generated constructor stub
			this.itemName=name;
			this.itemImage=image;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public int getItemImage() {
			return itemImage;
		}
		public void setItemImage(int itemImage) {
			this.itemImage = itemImage;
		}
	}
}
