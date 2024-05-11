package com.example.mobilejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class AromAndMedcPlantsActivity extends AppCompatActivity implements RecycleViewOnClick{

    private ImageView imgLogo ,imgMenu;
    private TextView aromAndMedcPlants, handyCrafts, craftProducts;
    private RelativeLayout expandableLayout;
    private Button btnPacks, btnCategories;
    private TextView bottomsheettitle;
    private boolean isExpanded = false;
    private RecyclerView aromAndMedcPlantsRecView;
    private aromAndMedcPlantsRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arom_and_medc_plants);
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
                    Intent intent = new Intent(AromAndMedcPlantsActivity.this , Main2Activity.class);
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
                startActivity(new Intent(AromAndMedcPlantsActivity.this, Main2Activity.class));
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

                startActivity(new Intent(AromAndMedcPlantsActivity.this, AboutUsActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AromAndMedcPlantsActivity.this, RegisterUser.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AromAndMedcPlantsActivity.this, LogInActivity.class));
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
                startActivity(new Intent(AromAndMedcPlantsActivity.this, BestSellerActivity.class));
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
                Intent intent = new Intent(AromAndMedcPlantsActivity.this , SubscriptionActivity.class);
                startActivity(intent);
            }
        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AromAndMedcPlantsActivity.this, Main2Activity.class));
            }
        });

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });


        handyCrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AromAndMedcPlantsActivity.this,HandyCarftsActivity.class);
                startActivity(intent);
            }
        });
        craftProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AromAndMedcPlantsActivity.this,CraftProductsActivity.class);
                startActivity(intent);
            }
        });
        aromAndMedcPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AromAndMedcPlantsActivity.this,AromAndMedcPlantsActivity.class);
                startActivity(intent);
            }
        });




        adapter = new aromAndMedcPlantsRecViewAdapter(this,this);
        aromAndMedcPlantsRecView = findViewById(R.id.aromAndMedcPlantsRecView);
        aromAndMedcPlantsRecView.setAdapter(adapter);

        LinearLayoutManager L1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        aromAndMedcPlantsRecView.setLayoutManager(L1);
        aromAndMedcPlantsRecView.setNestedScrollingEnabled(false);

        ArrayList<MostViewedHelperClass> I1 = new ArrayList<>();
        I1.add(new MostViewedHelperClass(R.drawable.huile_d_amande,"زيت اللوز","13.00DT","النباتات العطرية و الطبية"));
        I1.add(new MostViewedHelperClass(R.drawable.huile_nigelle,"زيت الحبة السوداء","12.00DT","النباتات العطرية و الطبية"));
        I1.add(new MostViewedHelperClass(R.drawable.huile_sesame,"زيت الجلجلان او السمسم","11.00DT","النباتات العطرية و الطبية"));
        I1.add(new MostViewedHelperClass(R.drawable.huile_jojoba,"زيت الجوجوبا","15.00DT","النباتات العطرية و الطبية"));
        adapter.setItems1(I1);
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
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        AromAndMedcPlantsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet));
                bottomsheetview.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AromAndMedcPlantsActivity.this,AromAndMedcPlantsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomsheetview);
                bottomSheetDialog.show();
                break;
            case 1 :
                BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(
                        AromAndMedcPlantsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview2= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_2,(LinearLayout)findViewById(R.id.bottom_sheet_2));
                bottomsheetview2.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AromAndMedcPlantsActivity.this,AromAndMedcPlantsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog2.dismiss();
                    }
                });
                bottomSheetDialog2.setContentView(bottomsheetview2);
                bottomSheetDialog2.show();
                break;
            case 2:
                BottomSheetDialog bottomSheetDialog3 = new BottomSheetDialog(
                        AromAndMedcPlantsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview3= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_3,(LinearLayout)findViewById(R.id.bottom_sheet_3));
                bottomsheetview3.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AromAndMedcPlantsActivity.this,AromAndMedcPlantsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog3.dismiss();
                    }
                });
                bottomSheetDialog3.setContentView(bottomsheetview3);
                bottomSheetDialog3.show();
                break;
            case 3:
                BottomSheetDialog bottomSheetDialog4 = new BottomSheetDialog(
                        AromAndMedcPlantsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview4= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_4,(LinearLayout)findViewById(R.id.bottom_sheet_4));
                bottomsheetview4.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AromAndMedcPlantsActivity.this,AromAndMedcPlantsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog4.dismiss();
                    }
                });
                bottomSheetDialog4.setContentView(bottomsheetview4);
                bottomSheetDialog4.show();
                break;


        }
    }

    @Override
    public void onLongItemClick(int position) {

    }
}