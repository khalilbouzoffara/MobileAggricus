package com.example.mobilejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView imgLogo,imgMenu;
    private TextView aromAndMedcPlants, handyCrafts, craftProducts;
    private Button btnPacks,btnCategories;
    private RelativeLayout expandableLayout;
    private boolean isExpanded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
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
                    Intent intent = new Intent(AboutUsActivity.this , Main2Activity.class);
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
                startActivity(new Intent(AboutUsActivity.this, Main2Activity.class));
            }
        });

        bestsellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this, BestSellerActivity.class));
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

                startActivity(new Intent(AboutUsActivity.this, AboutUsActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this, RegisterUser.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this, LogInActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
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
                Intent intent = new Intent(AboutUsActivity.this , SubscriptionActivity.class);
                startActivity(intent);
            }
        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutUsActivity.this, Main2Activity.class));
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
                Intent intent = new Intent(AboutUsActivity.this , AromAndMedcPlantsActivity.class);
                startActivity(intent);
            }

        });


        handyCrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUsActivity.this,HandyCarftsActivity.class);
                startActivity(intent);
            }
        });

        craftProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUsActivity.this,CraftProductsActivity.class);
                startActivity(intent);
            }
        });


    }

    private void InitializeViews(){
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
}

