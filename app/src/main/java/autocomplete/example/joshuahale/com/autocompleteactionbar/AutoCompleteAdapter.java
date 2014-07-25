package autocomplete.example.joshuahale.com.autocompleteactionbar;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * AutoCompleteAdapter is the ArrayAdapter that retrieves all of the Filtered Results. <br />
 * The FilteredResults are generated from the user's search query as they type. The Filter is only
 * triggered after the user has successfully entered two characters, it then begins to filter through
 * all of the possible results you have given it. <br />
 *
 * If you have the Places API implemented from Google, you can use this to Filter the input string
 * and retrieve local results pertaining to the user's current location. If you are doing local searches,
 * like searching through Contacts to find a specific contact, you would tie into the Contacts API in the
 * phone in the performFiltering method.
 *
 * @author Joshua Hale
 * @version 1.0
 */
public class AutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

    private ArrayList<String> resultList;
    private Context context;

    public AutoCompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int position) {
        return resultList.get(position);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected Filter.FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if(constraint != null) {
                    DummyValues dummyValues = new DummyValues();
                    resultList = dummyValues.getDummySuggestions(constraint.toString());

                    //-- Assign the values to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
