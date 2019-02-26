

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;

public class Menu extends ListActivity {

	String classes[] = { "AutoFitView", "CameraActivity", "Classifier" };

	@override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);

		//full Screen

	}

	@override
	protected void onListItemClick(ListView 1, View v, int position, long id) {

		super.onListItemClick(1,v,position,id);

		String c = classes[position];

		try {

		}
		catch {

		}
	}
	@override
	protected boolean onCreateOptionsMeny(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);


		return true;
	}

}