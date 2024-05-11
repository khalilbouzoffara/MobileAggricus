package com.example.mobilejava;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ImageView imgLogo,imgMenu;
    private TextView aromAndMedcPlants, handyCrafts, craftProducts;
    private Button btnPacks,btnCategories;
    private RelativeLayout expandableLayout;
    private boolean isExpanded = false;

    private ViewPager viewPager;
    private ViewPager viewPager2;
    private ArrayList<MostViewedHelperClass> modelArrayList1;
    private CardViewAdapter adapter;
    private CardViewAdapter2 adapter2;
    RecyclerView mostViewedRecycler;
    RecyclerView.Adapter adapterr;
    SliderView sliderView;


    int[] images = {R.drawable.banner_1, R.drawable.banner_4, R.drawable.banner_5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_main2);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        sliderView = findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        TextView more;
        more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, BestSellerActivity.class));

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav);
        View headerview = navigationView.getHeaderView(0);
        TextView aboutus = (TextView) headerview.findViewById(R.id.aboutUS);
        TextView register = (TextView) headerview.findViewById(R.id.registertext);
        TextView login = (TextView) headerview.findViewById(R.id.textView3);
        TextView logout = (TextView) headerview.findViewById(R.id.logOut);
        TextView HelloText = (TextView) headerview.findViewById(R.id.helloText);

        if (mAuth.getInstance().getCurrentUser() != null){

            register.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            HelloText.setVisibility(View.VISIBLE);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



            logout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FirebaseUser user = mAuth.getCurrentUser();
                }
            },1000);

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAuth.signOut();
                    Intent intent = new Intent(Main2Activity.this , Main2Activity.class);
                    startActivity(intent);

                }
            });

        }else {
            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            HelloText.setVisibility(View.GONE);


        }



        InitializeViewsMA();


        btnPacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this , SubscriptionActivity.class);
                startActivity(intent);
            }
        });


        btnCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExpanded) {
                    expandableLayout.setVisibility(View.GONE);
                    isExpanded = false;
                } else if (!(isExpanded)) {
                    expandableLayout.setVisibility(View.VISIBLE);
                    isExpanded = true;
                }
            }
        });

        aromAndMedcPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this , AromAndMedcPlantsActivity.class);
                startActivity(intent);
            }

        });


        handyCrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this,HandyCarftsActivity.class);
                startActivity(intent);
            }
        });

        craftProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this,CraftProductsActivity.class);
                startActivity(intent);
            }
        });



        Button outlinedButton;
        Button outlinedButton2;
        outlinedButton2 = findViewById(R.id.outlinedButton);
        outlinedButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.GONE);
            }
        });
        outlinedButton = findViewById(R.id.outlinedButton2);
        outlinedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setVisibility(View.GONE);

                viewPager2.setVisibility(View.VISIBLE);
            }
        });

        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        mostViewedRecycler();

        //todo principal w bestsellers click listenener
        TextView principal = (TextView) headerview.findViewById(R.id.textView7);
        TextView bestsellers = (TextView) headerview.findViewById(R.id.textView6);
        ImageView twitter = (ImageView) headerview.findViewById(R.id.imageView);
        ImageView facebook = (ImageView) headerview.findViewById(R.id.imageView5);
        ImageView youtube = (ImageView) headerview.findViewById(R.id.imageView8);
        ImageView instagram = (ImageView) headerview.findViewById(R.id.imageView7);
        ImageView google = (ImageView) headerview.findViewById(R.id.imageView6);
        FloatingActionButton button = headerview.findViewById(R.id.floating_action_button);

        bestsellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, BestSellerActivity.class));
            }
        });

        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, Main2Activity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://www.facebook.com/aggricus");
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main2Activity.this, AboutUsActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, RegisterUser.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, LogInActivity.class));
            }
        });

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, Main2Activity.class));
            }
        });


        viewPager2 = findViewById(R.id.viewerpage2);
        viewPager = findViewById(R.id.viewerpage);
        loadcard();
        loadcard2();


        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });


    }
    private void InitializeViewsMA(){
        imgLogo =findViewById(R.id.imgLogo);
        imgMenu =findViewById(R.id.imgMenu);
        imgMenu = findViewById(R.id.imgMenu);
        btnPacks = findViewById(R.id.btnPacks);
        btnCategories = findViewById(R.id.btnCategories);
        expandableLayout=findViewById(R.id.expandableLayout);
        aromAndMedcPlants=findViewById(R.id.aromAndMedcPlants);
        handyCrafts=findViewById(R.id.handyCrafts);
        craftProducts=findViewById(R.id.craftProducts);
    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void loadcard() {

        modelArrayList1 = new ArrayList<>();
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.huile_d_amande,"زيت اللوز","13.00DT","النباتات العطرية و الطبية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.huile_nigelle,"زيت الحبة السوداء","12.00DT","النباتات العطرية و الطبية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.huile_sesame,"زيت الجلجلان او السمسم","11.00DT","النباتات العطرية و الطبية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.huile_jojoba,"زيت الجوجوبا","15.00DT","النباتات العطرية و الطبية"));
        adapter2 = new CardViewAdapter2(this, modelArrayList1);
        viewPager.setAdapter(adapter2);
        viewPager.setPadding(40, 0, 100, 0);
    }

    private void loadcard2() {


        modelArrayList1 = new ArrayList<>();
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.maarr_cuup,"كأس عروسة و عريس","45.00DT","صناعات يدوية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.coffreeeeeeee,"كوفريه تونسي","65.00DT","صناعات يدوية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.cup_as,"كأس حسب الطلب","45.00DT","صناعات يدوية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.spoon,"ملعقة كاب كايك","10.00DT","صناعات يدوية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.girl_cup,"كأس عروسة","45.00DT","صناعات يدوية"));
        modelArrayList1.add(new MostViewedHelperClass(R.drawable.dabdoub_cup,"كأس دبدوب","40.00DT","صناعات يدوية"));
        adapter2 = new CardViewAdapter2(this, modelArrayList1);
        viewPager2.setAdapter(adapter2);
        viewPager2.setPadding(40, 0, 100, 0);


    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.babouch_khomsa, "ببوش نسائي خمسة", "40.00DT", " افضل العروض"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.dabdoub_cup, "كأس دبدوب", "40.00DT", "  افضل العروض"));

        adapterr = new MostViewedAdpater(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapterr);
        mostViewedRecycler.setPadding(60, 0, 100, 0);

    }
}