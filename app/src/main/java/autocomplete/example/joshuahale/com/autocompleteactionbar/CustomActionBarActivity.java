package autocomplete.example.joshuahale.com.autocompleteactionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;

/**
 * This example application will show you how to use an AutoCompleteTextView inside of the ActionBar
 * instead of having to overcomplicate things with the SearchView. While the SearchView is helpful,
 * the code is pretty ugly in terms of having to call things dynamically from the code. Having to set each
 * property for each part of the view dynamically can get really cumbersome. <br /> <br />
 *
 * Using this method you can customize how your action bar layout looks (if you want to) or you can just leave
 * the entire window open as an AutoCompleteTextView field and let the Adapter drop down.
 *
 * @author Joshua Hale (July 24, 2014)
 * @version 1.0
 */
public class CustomActionBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_action_bar);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_action_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        //-- This listener is directly tied to the search item MenuItem. By doing this
        //-- we are able to listen for the events and trigger other things to happen in
        //-- the ActionBar as we see fit.
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {

                toggleMenuItems(menu, false);
                return true;                    //-- This must return true or your expand touch will not be consumed
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                toggleMenuItems(menu, true);
                return true;                    //-- This must return true or your expand touch will not be consumed
            }
        });

        View v = searchItem.getActionView();

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)v.findViewById(R.id.search_edit_text);
        autoCompleteTextView.setAdapter(new AutoCompleteAdapter(this, R.layout.list_item));

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Toggles the menu item to be visible or invisible depending on the value it gets sent. <br />
     * If the value is true, that means the action view has been collapsed and we show our icons as normal. <br />
     * If the value is false, that means the action view is visible and we hide all of our icons.
     * @param menu <b>(Menu)</b> - Menu that has been added to the Activity
     * @param visible <b>(boolean)</b> - Boolean value for the visibility of all menu items
     */
    private void toggleMenuItems(Menu menu, boolean visible) {
        menu.findItem(R.id.action_search).setVisible(visible);
        menu.findItem(R.id.action_add).setVisible(visible);
    }
}
