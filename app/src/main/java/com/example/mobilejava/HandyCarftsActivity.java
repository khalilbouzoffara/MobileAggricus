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

public class HandyCarftsActivity extends AppCompatActivity implements RecycleViewOnClick{
    private RecyclerView handyCraftsRecView;
    private ImageView imgLogo ,imgMenu;
    private TextView aromAndMedcPlants, handyCrafts, craftProducts;
    private RelativeLayout expandableLayout;
    private Button btnPacks, btnCategories;
    private boolean isExpanded = false;
    TextView bottomsheettitle ;
    private HandyCraftsRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handy_carfts);
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
                    Intent intent = new Intent(HandyCarftsActivity.this , Main2Activity.class);
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
                startActivity(new Intent(HandyCarftsActivity.this, Main2Activity.class));
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

                startActivity(new Intent(HandyCarftsActivity.this, AboutUsActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HandyCarftsActivity.this, RegisterUser.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HandyCarftsActivity.this, LogInActivity.class));
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
                startActivity(new Intent(HandyCarftsActivity.this, BestSellerActivity.class));
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
                Intent intent = new Intent(HandyCarftsActivity.this , SubscriptionActivity.class);
                startActivity(intent);
            }
        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HandyCarftsActivity.this, Main2Activity.class));
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
                Intent intent = new Intent(HandyCarftsActivity.this , AromAndMedcPlantsActivity.class);
                startActivity(intent);
            }

        });

        handyCrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HandyCarftsActivity.this,HandyCarftsActivity.class);
                startActivity(intent);
            }
        });

        craftProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HandyCarftsActivity.this,CraftProductsActivity.class);
                startActivity(intent);
            }
        });


        adapter = new HandyCraftsRecViewAdapter(this,this);
        handyCraftsRecView = findViewById(R.id.handyCraftsRecView);
        handyCraftsRecView.setAdapter(adapter);

        LinearLayoutManager L2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        handyCraftsRecView.setLayoutManager(L2);
        handyCraftsRecView.setHasFixedSize(false);
        handyCraftsRecView.setNestedScrollingEnabled(false);

        ArrayList<MostViewedHelperClass> I2 = new ArrayList<>();
        I2.add(new MostViewedHelperClass(R.drawable.maarr_cuup,"كأس عروسة و عريس","45.00DT","صناعات يدوية"));
        I2.add(new MostViewedHelperClass(R.drawable.coffreeeeeeee,"كوفريه تونسي","65.00DT","صناعات يدوية"));
        I2.add(new MostViewedHelperClass(R.drawable.cup_as,"كأس حسب الطلب","45.00DT","صناعات يدوية"));
        I2.add(new MostViewedHelperClass(R.drawable.spoon,"ملعقة كاب كايك","10.00DT","صناعات يدوية"));
        I2.add(new MostViewedHelperClass(R.drawable.girl_cup,"كأس عروسة","45.00DT","صناعات يدوية"));
        I2.add(new MostViewedHelperClass(R.drawable.dabdoub_cup,"كأس دبدوب","40.00DT","صناعات يدوية"));
        adapter.setItems2(I2);
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
                BottomSheetDialog bottomSheetDialog5 = new BottomSheetDialog(
                        HandyCarftsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview5= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_5,(LinearLayout)findViewById(R.id.bottom_sheet_5));
                bottomsheetview5.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HandyCarftsActivity.this , HandyCarftsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog5.dismiss();
                    }
                });
                bottomSheetDialog5.setContentView(bottomsheetview5);
                bottomSheetDialog5.show();
                break;
            case 1 :
                BottomSheetDialog bottomSheetDialog6 = new BottomSheetDialog(
                        HandyCarftsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview6= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_6,(LinearLayout)findViewById(R.id.bottom_sheet_6));
                bottomsheetview6.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HandyCarftsActivity.this , HandyCarftsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog6.dismiss();
                    }
                });
                bottomSheetDialog6.setContentView(bottomsheetview6);
                bottomSheetDialog6.show();
                break;

            case 2:
                BottomSheetDialog bottomSheetDialog7 = new BottomSheetDialog(
                        HandyCarftsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview7= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_7,(LinearLayout)findViewById(R.id.bottom_sheet_7));
                bottomsheetview7.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HandyCarftsActivity.this , HandyCarftsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog7.dismiss();
                    }
                });
                bottomSheetDialog7.setContentView(bottomsheetview7);
                bottomSheetDialog7.show();
                break;

            case 3:
                BottomSheetDialog bottomSheetDialog8 = new BottomSheetDialog(
                        HandyCarftsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview8= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_8,(LinearLayout)findViewById(R.id.bottom_sheet_8));
                bottomsheetview8.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HandyCarftsActivity.this , HandyCarftsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog8.dismiss();
                    }
                });
                bottomSheetDialog8.setContentView(bottomsheetview8);
                bottomSheetDialog8.show();
                break;

            case 4:
                BottomSheetDialog bottomSheetDialog9 = new BottomSheetDialog(
                        HandyCarftsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview9= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_9,(LinearLayout)findViewById(R.id.bottom_sheet_9));
                bottomsheetview9.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HandyCarftsActivity.this , HandyCarftsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog9.dismiss();
                    }
                });
                bottomSheetDialog9.setContentView(bottomsheetview9);
                bottomSheetDialog9.show();
                break;

            case 5:
                BottomSheetDialog bottomSheetDialog10 = new BottomSheetDialog(
                        HandyCarftsActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomsheetview10= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_10,(LinearLayout)findViewById(R.id.bottom_sheet_10));
                bottomsheetview10.findViewById(R.id.btnBottomsheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HandyCarftsActivity.this , HandyCarftsActivity.class);
                        startActivity(intent);
                        bottomSheetDialog10.dismiss();
                    }
                });
                bottomSheetDialog10.setContentView(bottomsheetview10);
                bottomSheetDialog10.show();
                break;


        }
    }

    @Override
    public void onLongItemClick(int position) {

    }
}