package will.tw.carepets;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import will.tw.carepets.model.Results;

/**
 * Created by william on 2016/12/21.
 */

public class DataActivity extends AppCompatActivity {
    private ProgressBar dataprogressBar;
    private ImageView imageview;
    private TextView dataname, datasex, datatype, databuild, dataage, datavariety, datareason, dataacceptnum, datachipnum, dataissterill,
            datahairtype, datanote, dataResettlement, dataphone, dataemail, dataChildreAnlong, dataAnimalAnlong, dataBodyweight;
    private Context context;
    private Toolbar toolbar;
    private TableLayout tbl;
    CollapsingToolbarLayout collapsingToolbarLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_pet);

        imageview = (ImageView) findViewById(R.id.image);
        dataname = (TextView) findViewById(R.id.dataname);
        datasex = (TextView) findViewById(R.id.datasex);
        datatype = (TextView) findViewById(R.id.datatype);
        databuild = (TextView) findViewById(R.id.databuild);
        dataage = (TextView) findViewById(R.id.dataage);
        datavariety = (TextView) findViewById(R.id.datavariety);
        datareason = (TextView) findViewById(R.id.datareason);
        dataacceptnum = (TextView) findViewById(R.id.dataacceptnum);
        datachipnum = (TextView) findViewById(R.id.datachipnum);
        dataissterill = (TextView) findViewById(R.id.dataissterill);
        datahairtype = (TextView) findViewById(R.id.datahairtype);
        datanote = (TextView) findViewById(R.id.datanote);
        dataResettlement = (TextView) findViewById(R.id.dataResettlement);
        dataphone = (TextView) findViewById(R.id.dataphone);
        dataemail = (TextView) findViewById(R.id.dataemail);
        dataChildreAnlong = (TextView) findViewById(R.id.dataChildreAnlong);
        dataAnimalAnlong = (TextView) findViewById(R.id.dataAnimalAnlong);
        dataBodyweight = (TextView) findViewById(R.id.dataBodyweight);


        String json = getIntent().getStringExtra("itemJson");
        Log.e("Ten", "result: " + json);
        Gson gson = new Gson();
        Results results = gson.fromJson(json, Results.class);

        Picasso.with(context)
                .load(results.getImageName())
                .into(imageview);
        dataname.setText(results.getName());
        datasex.setText(results.getSex());
        datatype.setText(results.getType());
        databuild.setText(results.getBuild());
        dataage.setText(results.getAge());
        datavariety.setText(results.getVariety());
        datareason.setText(results.getReason());
        dataacceptnum.setText(results.getAcceptNum());
        datachipnum.setText(results.getChipNum());
        dataissterill.setText(results.getIsSterilization());
        datahairtype.setText(results.getHairType());
        datanote.setText(results.getNote());
        dataResettlement.setText(results.getResettlement());
        dataphone.setText(results.getPhone());
        dataemail.setText(results.getEmail());
        dataChildreAnlong.setText(results.getChildreAnlong());
        dataAnimalAnlong.setText(results.getAnimalAnlong());
        dataBodyweight.setText(results.getBodyweight());


        Log.e("Ten", "ssss" + results.toString());
        initToolbar();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        tbl = (TableLayout)findViewById(R.id.tmall);
        tbl.setColumnShrinkable(1,true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
//        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }
}
