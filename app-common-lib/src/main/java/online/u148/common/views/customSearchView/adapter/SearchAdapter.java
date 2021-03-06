package online.u148.common.views.customSearchView.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.nodescm.app.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liurui on 2016/10/12.
 */
public class SearchAdapter extends BaseAdapter implements Filterable {

    private ArrayList<String> data;
    private String[] suggestions;
    private Drawable suggestionIcon;
    private LayoutInflater inflater;
    private boolean ellipsize;

    public SearchAdapter(Context context, String[] suggestions) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        this.suggestions = suggestions;
    }

    public SearchAdapter(Context context, String[] suggestions, Drawable suggestionIcon, boolean ellipsize) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        this.suggestions = suggestions;
        this.suggestionIcon = suggestionIcon;
        this.ellipsize = ellipsize;
    }

    public void setSuggestions(String[] suggestions) {
        this.suggestions = suggestions;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuggestionsViewHolder viewHolder;

        convertView = inflater.inflate(R.layout.suggest_item, parent, false);

        TextView textView = (TextView) convertView.findViewById(R.id.suggestion_text);
        View divideLine = convertView.findViewById(R.id.item_divider);

        String currentListData = (String) getItem(position);

        textView.setText(currentListData);
        if (ellipsize) {
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }

        if (position == data.size() -1) {
            divideLine.setVisibility(View.GONE);
        }

//        textView = (TextView) convertView.findViewById(R.id.suggestion_text);
//        divideLine = convertView.findViewById(R.id.item_divider);

//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.suggest_item, parent, false);
//
//            viewHolder = new SuggestionsViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (SuggestionsViewHolder) convertView.getTag();
//        }
//
//        viewHolder.textView.setText(currentListData);
//        if (ellipsize) {
//            viewHolder.textView.setSingleLine();
//            viewHolder.textView.setEllipsize(TextUtils.TruncateAt.END);
//        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<String> searchData = new ArrayList<>();
                if (!TextUtils.isEmpty(constraint)) {
                    if (suggestions != null) {
                        for (String string : suggestions) {
                            if (TextUtils.isEmpty(string)){
                                break;
                            }
                            if (string.toLowerCase().contains(constraint.toString().toLowerCase())) {
                                searchData.add(string);
                            }
                        }
                    }
                }else {
                    if (suggestions != null){
                        for (int i =0; i<suggestions.length; i++){
                            if (!TextUtils.isEmpty(suggestions[i])){
                                searchData.add(suggestions[i]);
                            }
                        }
                    }
                }
                filterResults.values = searchData;
                filterResults.count = searchData.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.values != null) {
                    data = (ArrayList<String>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
        return filter;
    }

    private class SuggestionsViewHolder {

        TextView textView;
//        ImageView imageView;
        View divideLine;

        public SuggestionsViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.suggestion_text);
            divideLine = convertView.findViewById(R.id.item_divider);
            if (suggestionIcon != null) {
//                imageView = (ImageView) convertView.findViewById(R.id.suggestion_icon);
//                imageView.setImageDrawable(suggestionIcon);
            }
        }
    }
}
