package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView smallImage;
    TextView name;
    TextView description;
    ImageView bigImage;

    public UserViewHolder(View itemView) {
        super(itemView);
        smallImage = itemView.findViewById(R.id.imageView2);
        name = itemView.findViewById(R.id.textView5);
        description = itemView.findViewById(R.id.textView2);
        bigImage = itemView.findViewById((R.id.imageView9));

        smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating alert dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle("Profile");
                builder.setMessage(name.getText());
                builder.setCancelable(true);

                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });
                builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Create intent to start another activity
                        Intent activityList = new Intent(itemView.getContext(), MainActivity.class);
                        activityList.putExtra("Name", String.valueOf(name.getText()));
                        activityList.putExtra("Description", String.valueOf(description.getText()));
                        startActivity(itemView.getContext(),activityList,null);
                    }
                });
                AlertDialog alert = builder.create(); //Display alert when the image is clicked
                alert.show();
            }
        });
    }
}