package will.tw.carepets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import will.tw.carepets.api.PetApi;
import will.tw.carepets.api.PetApiNon;
import will.tw.carepets.model.PetReport;
import will.tw.carepets.model.Results;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private ProgressBar progressBar;

//    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.dogbo);
        bottomNavigationBar.setMode(BottomNavigationBar.FOCUSABLES_TOUCH_MODE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.all, getString(R.string.navvarall)).setActiveColor(R.color.cardview_dark_background))
                .addItem(new BottomNavigationItem(R.drawable.dog, getString(R.string.navdog)).setActiveColor(R.color.cardview_dark_background))
                .addItem(new BottomNavigationItem(R.drawable.cat, getString(R.string.navcat)).setActiveColor(R.color.cardview_dark_background))
                .addItem(new BottomNavigationItem(R.drawable.other, getString(R.string.navother)).setActiveColor(R.color.cardview_dark_background))
                .setFirstSelectedPosition(0)
                .initialise();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sysusnon();

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                android.app.FragmentManager fm = MainActivity.this.getFragmentManager();
                android.app.FragmentTransaction transaction = fm.beginTransaction();
                switch (position) {
                    case 0:
                        sysusnon();
                        break;
                    case 1:
                        sysus("犬");

                        break;
                    case 2:
                        sysus("貓");

                        break;
                    case 3:
                        sysus("其他");
                        break;
                    default:
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all) {
            sysusnon();
            // Handle the camera action
        } else if (id == R.id.dog) {
            sysus("犬");
        } else if (id == R.id.cat) {
            sysus("貓");
        } else if (id == R.id.othrt) {
            sysus("其他");
        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private class PetSubscriber extends Subscriber<PetReport> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(PetReport pet) {
            probar();
            String text;
            Results[] resultsu = pet.result.results;
            List<Results> posts = new ArrayList<Results>();
            posts = Arrays.asList(resultsu);
//            final String text = pet.result.results[0].Name;
            text = resultsu.toString();
            System.out.println(text);
            adapter = new RecyclerViewAdapter(MainActivity.this, posts);
            recyclerView.setAdapter(adapter);
//            Log.e("william", text);

        }
    }

    public void sysus(String type) {
        final Scheduler newThread = Schedulers.newThread();
        final Scheduler mainThread = AndroidSchedulers.mainThread();
        PetSubscriber subscriber = new PetSubscriber();
        PetApi.findReportByCity(type)
                .subscribeOn(newThread)
                .observeOn(mainThread)
                .subscribe(subscriber);
    }

    public void sysusnon() {
        final Scheduler newThread = Schedulers.newThread();
        final Scheduler mainThread = AndroidSchedulers.mainThread();
        PetSubscriber subscriber = new PetSubscriber();
        PetApiNon.findReportByCity()
                .subscribeOn(newThread)
                .observeOn(mainThread)
                .subscribe(subscriber);
    }

    public void probar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

        }
    }
}
