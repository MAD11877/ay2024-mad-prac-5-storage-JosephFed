package sg.edu.np.mad.madpractical5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final ListActivity activity;
    private ArrayList<User> list_objects;
    public UserAdapter(ArrayList<User> list_object, ListActivity activity){
        this.list_objects = list_object;
        this.activity = activity;
    }

    //Method to create a view holder for a username
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    //Method to bind username to viewholder
    public void onBindViewHolder(UserViewHolder holder, int position){
        //get position of username
        User list_items = list_objects.get(position);
        //Set username to the viewholder based on custom_activity_list.xml
        holder.name.setText(list_items.getName());
        //Set description to the viewholder based on custom_activity_list.xml
        holder.description.setText(list_items.getDescription());
        //Show big image if last digit is 7
        String str = String.valueOf(holder.name.getText());
        char lastchar = str.charAt(str.length() - 1);
        if (String.valueOf(lastchar).equals("7")){
            holder.bigImage.setVisibility(View.VISIBLE);
        }
        else {
            holder.bigImage.setVisibility(View.GONE);
        }
        //Configure setonclicklistener() for the small image on the view holder based on custom_activity_list.xml

    }
    public int getItemCount() {
        return list_objects.size();
    }
}
