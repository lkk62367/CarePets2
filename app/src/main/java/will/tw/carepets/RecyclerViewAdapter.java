package will.tw.carepets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import will.tw.carepets.model.PetReport;
import will.tw.carepets.model.Results;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Results> resultsList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Results> resultsList) {
        this.resultsList = resultsList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pet, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView,parent.getContext());
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        Picasso.with(context)
                .load(resultsList.get(position).getImageName())
//                .resize(150, 150)
                .into(holder.petimage);
        holder.petname.setText("姓名: "+resultsList.get(position).getName());
        holder.petsex.setText("性別: " + resultsList.get(position).getSex());
        holder.pettype.setText("種類: " + resultsList.get(position).getType());
        holder.petbuild.setText("體型："+ resultsList.get(position).getBuild());
        Gson gson = new Gson();
        holder.itemJson = gson.toJson(resultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.resultsList.size();
    }
}
