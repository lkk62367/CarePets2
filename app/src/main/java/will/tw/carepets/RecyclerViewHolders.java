package will.tw.carepets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public String itemJson;
    public ImageView petimage;
    public TextView petname;
    public TextView petsex;
    public TextView pettype;
    public TextView petbuild;
    public Context context;
    public RecyclerViewHolders(View itemView, Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        petimage = (ImageView)itemView.findViewById(R.id.imageView);
        petname = (TextView)itemView.findViewById(R.id.petname);
        petsex = (TextView)itemView.findViewById(R.id.petsex);
        pettype = (TextView)itemView.findViewById(R.id.pettype);
        petbuild = (TextView)itemView.findViewById(R.id.build);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        ((AppCompatActivity)context).startActivityForResult(new Intent(context,DataActivity.class).putExtra("Name",petname.getText()).putExtra("itemJson",itemJson),1);
    }

}
