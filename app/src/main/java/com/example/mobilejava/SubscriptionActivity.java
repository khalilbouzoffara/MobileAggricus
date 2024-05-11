package com.example.mobilejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SubscriptionActivity extends AppCompatActivity {

    private ImageView imgLogo ,imgMenu;
    private TextView aromAndMedcPlants, handyCrafts, craftProducts;
    private RelativeLayout expandableLayout;
    private Button btnPacks, btnCategories, btnRegister1, btnRegister2, btnRegister3;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        InitializeViewsSA();
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
                    Intent intent = new Intent(SubscriptionActivity.this , Main2Activity.class);
                    startActivity(intent);

                }
            });

        }else {
            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            HelloText.setVisibility(View.GONE);


        }

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
                Intent intent = new Intent(SubscriptionActivity.this , SubscriptionActivity.class);
                startActivity(intent);
            }
        });

        handyCrafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionActivity.this,HandyCarftsActivity.class);
                startActivity(intent);
            }
        });

        aromAndMedcPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionActivity.this , AromAndMedcPlantsActivity.class);
                startActivity(intent);
            }

        });

        craftProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionActivity.this,CraftProductsActivity.class);
                startActivity(intent);
            }
        });

        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionActivity.this , FormActivity.class);
                startActivity(intent);
            }
        });

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionActivity.this , FormActivity.class);
                startActivity(intent);
            }
        });

        btnRegister3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionActivity.this , FormActivity.class);
                startActivity(intent);
            }
        });

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

        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, Main2Activity.class));
            }
        });

        bestsellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, BestSellerActivity.class));
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

                startActivity(new Intent(SubscriptionActivity.this, AboutUsActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, RegisterUser.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, LogInActivity.class));
            }
        });

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, Main2Activity.class));
            }
        });



    }
    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void InitializeViewsSA(){
        imgLogo = findViewById(R.id.imgLogo);
        imgMenu = findViewById(R.id.imgMenu);
        btnPacks = findViewById(R.id.btnPacks);
        btnCategories = findViewById(R.id.btnCategories);
        btnRegister1 = findViewById(R.id.btnRegister1);
        btnRegister2 = findViewById(R.id.btnRegister2);
        btnRegister3 = findViewById(R.id.btnRegister3);
        expandableLayout=findViewById(R.id.expandableLayout);
        aromAndMedcPlants=findViewById(R.id.aromAndMedcPlants);
        handyCrafts=findViewById(R.id.handyCrafts);
        craftProducts=findViewById(R.id.craftProducts);
    }

}