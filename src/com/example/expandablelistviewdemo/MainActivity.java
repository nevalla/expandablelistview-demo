package com.example.expandablelistviewdemo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;

/**
 * Example application with ExpandableListView and CheckedTextView as list item.
 * Texts of selected list items are displayed in parent view.
 * 
 * @author Lauri Nevala
 * 
 *
 */
public class MainActivity extends Activity {
	
	private SettingsListAdapter adapter;
	private ExpandableListView categoriesList;
	private ArrayList<Category> categories;
	
	protected Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		categoriesList = (ExpandableListView)findViewById(R.id.categories);
		categories = Category.getCategories();
		adapter = new SettingsListAdapter(this, 
				categories, categoriesList);
        categoriesList.setAdapter(adapter);
        
        categoriesList.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				
				CheckedTextView checkbox = (CheckedTextView)v.findViewById(R.id.list_item_text_child);
				checkbox.toggle();
				
				
				// find parent view by tag
				View parentView = categoriesList.findViewWithTag(categories.get(groupPosition).name);
				if(parentView != null) {
					TextView sub = (TextView)parentView.findViewById(R.id.list_item_text_subscriptions);
					
					if(sub != null) {
						Category category = categories.get(groupPosition);
						if(checkbox.isChecked()) {
							// add child category to parent's selection list
							category.selection.add(checkbox.getText().toString());
							
							// sort list in alphabetical order
							Collections.sort(category.selection, new CustomComparator());
						}
						else {
							// remove child category from parent's selection list
							category.selection.remove(checkbox.getText().toString());
						}		
						
						// display selection list
						sub.setText(category.selection.toString());
					}
				}				
				return true;
			}
		});
	}
	
	public class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
    
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
