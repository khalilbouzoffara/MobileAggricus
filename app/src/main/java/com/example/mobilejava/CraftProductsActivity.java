package com.example.mobilejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CraftProductsActivity extends AppCompatActivity implements RecycleViewOnClick{
    private ImageView imgLogo,imgMenu;
    private TextView aromAndMedcPlants, handyCrafts, craftProducts;
    private RelativeLayout expandableLayout;
    private Button btnPacks, btnCategories;
    private boolean isExpanded = false;
    private RecyclerView craftProductsRecView;
    TextView bottomsheettitle ;
    private craftProductsRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft_products);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        InitializeViews();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav);
        View headerview = navigationView.getHeaderView(0);
        TextView aboutus = (TextView) headerview.findViewById(R.id.aboutUS);
        TextView register = (TextView) headerview.findViewById(R.id.registertext);
        TextView login = (TextView) headerview.findViewById(R.id.textView3);
        TextView logout = (TextView) headerview.findViewById(R.id.logOut);
        TextView HelloText = (TextView) headerview.findViewById(R.id.helloText);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        //todo principal w bestsellers click listenener
        TextView principal = (TextView) headerview.findViewById(R.id.textView7);
        TextView bestsellers = (TextView) headerview.findViewById(R.id.textView6);
        ImageView twitter = (ImageView) headerview.findViewById(R.id.imageView);
        ImageView facebook = (ImageView) headerview.findViewById(R.id.imageView5);
        ImageView youtube = (ImageView) headerview.findViewById(R.id.imageView8);
        ImageView instagram = (ImageView) headerview.findViewById(R.id.imageView7);
        ImageView google = (ImageView) headerview.findViewById(R.id.imageView6);
        FloatingActionButton button = headerview.findViewById(R.id.floating_action_button);

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
                    Intent intent = new Intent(CraftProductsActivity.this , Main2Activity.class);
                    startActivity(intent);

                }
            });

        }else {
            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            HelloText.setVisibility(View.GONE);


        }

        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CraftProductsActivity.this, Main2Activity.class));
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

                startActivity(new Intent(CraftProductsActivity.this, AboutUsActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CraftProductsActivity.this, RegisterUser.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CraftProductsActivity.this, LogInActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        bestsellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CraftProductsActivity.this, BestSellerActivity.class));
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

        btnPacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CraftProductsActivity.this , SubscriptionActivity.class);
                startActivity(intent);
            }
        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CraftProductsActivity.this, Main2Activity.class));
            }
        });

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });

        aromAndMedcPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CraftProductsActivity.this , AromAndMedcPlantsActivity.class);
                startActivity(intent);
            }

        });


        handyCrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CraftProductsActivity.this,HandyCarftsActivity.class);
                startActivity(intent);
            }
        });

        craftProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CraftProductsActivity.this,CraftProductsActivity.class);
                startActivity(intent);
            }
        });

        adapter = new craftProductsRecViewAdapter(this,this);
        craftProductsRecView=findViewById(R.id.craftProductsRecView);
        craftProductsRecView.setAdapter(adapter);

        LinearLayoutManager  L3 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        craftProductsRecView.setLayoutManager(L3);
        craftProductsRecView.setHasFixedSize(false);
        craftProductsRecView.setNestedScrollingEnabled(false);

        ArrayList<MostViewedHelperClass> I3 = new ArrayList<>();
        I3.add(new MostViewedHelperClass(R.drawable.babouch_feminin,"ببوش نسائي تريزة","45.00dt","منتوجات حرفية"));
        I3.add(new MostViewedHelperClass(R.drawable.babouch_khomsa,"ببوش نسائي خمسة","40.00dt","منتوجات حرفية"));
        adapter.setItems3(I3);


    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void InitializeViews(){

        imgLogo = findViewById(R.id.imgLogo);
        imgMenu = findViewById(R.id.imgMenu);
        btnPacks = findViewById(R.id.btnPacks);
        btnCategories = findViewById(R.id.btnCategories);
        expandableLayout=findViewById(R.id.expandableLayout);
        aromAndMedcPlants=findViewById(R.id.aromAndMedcPlants);
        handyCrafts=findViewById(R.id.handyCrafts);
        craftProducts=findViewById(R.id.craftProducts);
    }

    @Override
    public void onItemClick(int positon) {
        switch (positon){
            case 0 :

                bottomsheettitle=(TextView) findViewById(R.id.bottom_sheet_title);
                BottomSheetDialog bottomSheetDialog11 = new BottomSheetDialog(
                        CraftProductsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview11= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_11,(LinearLayout)findViewById(R.id.bottom_sheet_11));
                bottomsheetview11.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CraftProductsActivity.this,CraftProductsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog11.dismiss();
                    }
                });
                bottomSheetDialog11.setContentView(bottomsheetview11);
                bottomSheetDialog11.show();
                break;
            case 1 :
                BottomSheetDialog bottomSheetDialog12 = new BottomSheetDialog(
                        CraftProductsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview12= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_12,(LinearLayout)findViewById(R.id.bottom_sheet_12));
                bottomsheetview12.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CraftProductsActivity.this,CraftProductsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog12.dismiss();
                    }
                });
                bottomSheetDialog12.setContentView(bottomsheetview12);
                bottomSheetDialog12.show();
                break;


        }
    }

    @Override
    public void onLongItemClick(int position) {

    }
}