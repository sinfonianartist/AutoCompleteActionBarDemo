package autocomplete.example.joshuahale.com.autocompleteactionbar;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * This method is a dummy class. It's sole purpose is so that you can see how the AutoCompleteTextView
 * properly gets the values from it's data sources. There are some helper functions down at the bottom
 * I have used in the past in order to help regulate some of the ways the different suggestions have been
 * shown.
 */
public class DummyValues {

    public DummyValues() {}

    public ArrayList<String> getDummySuggestions(String input) {

        ArrayList<String> dummySuggestions = new ArrayList<String>();

        for(int i = 0; i < 10; i++) {
            dummySuggestions.add("Dummy Suggestion " + i);
        }

        dummySuggestions = removeDuplicateSuggestions(dummySuggestions);
        dummySuggestions = getSortedSearchSuggestions(dummySuggestions, input);

        return dummySuggestions;
    }

    /**
     * Removes exact duplicates from the suggestions after the entire list has been compiled. <br />
     * The use of a LinkedHashSet here allows us to save the order of the items in the ArrayList (in case
     * they have already been sorted to our liking). By converting an ArrayList to a LinkedHashSet and
     * converting it back to an ArrayList, we get rid of duplicate values without having to iterate
     * through the list for each element and replacing and removing them. This allows the runtime for the
     * removal of duplicate Suggestions to run at O(n) time rather than O(n^2) runtime.
     * @param rawSuggestions <b>(ArrayList)</b> - Sorted or unsorted ArrayList of all suggestions
     * @return <b>(ArrayList)</b> - Suggestions with exact duplicates removed
     */
    private ArrayList<String> removeDuplicateSuggestions(ArrayList<String> rawSuggestions) {
        Set<String> set = new LinkedHashSet<String>();
        set.addAll(rawSuggestions);

        ArrayList<String> suggestions = new ArrayList<String>();
        suggestions.addAll(set);

        return suggestions;
    }

    /**
     * This method retrieves the matching values of the suggestions. Since most values that are stored
     * within various databases are of mixed case, we should convert them all to a single case to verify
     * that we are indeed grabbing matching suggestions.
     * @param rawSuggestions <b>(ArrayList)</b> - Entire ArrayList of suggestions
     * @param input <b>(String)</b> - User input from AutoCompleteTextView
     * @return <b>(ArrayList)</b> - Suggestions sorted by user's criteria
     */
    private ArrayList<String> getSortedSearchSuggestions(ArrayList<String> rawSuggestions, String input) {
        ArrayList<String> sortedSuggestions = new ArrayList<String>();

        for(String s : rawSuggestions) {
            if(s.toLowerCase(Locale.getDefault()).contains(input.toLowerCase(Locale.getDefault()))) {
                sortedSuggestions.add(s);
            }
        }

        return sortedSuggestions;
    }
}
