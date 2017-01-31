package com.nikita.firststep.activity.activity.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import nikita.myappfirststep.R;

public abstract class StartConfig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setViewPager(CustomPagerAdapter adapter) {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(adapter);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.object_sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void setToolbar(int toolbarId) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);

        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    public void setCollapsingToolbar(String title, int toolbarId) {
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(toolbarId);
        collapsingToolbar.setTitle(title);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
    }

    public abstract class CustomPagerAdapter extends PagerAdapter implements OnMapReadyCallback {

        protected Context context;
        protected GoogleMap mMap;

        public CustomPagerAdapter(Context context) {
            super();
            this.context = context;
        }

        @Override
        public abstract Object instantiateItem(ViewGroup collection, int position);

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Информация";
                case 1: return "Как добраться";
                case 2: return "На карте";
            }
            return null;
        }

        /**
         * This method sets rounded corners to ImageView
         * @param imageView - image which will have rounded corners
         */
        protected void setImageCorners(ImageView imageView, int id) {
            Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(id)).getBitmap();
            Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
            Canvas canvas = new Canvas(imageRounded);
            Paint mpaint = new Paint();
            mpaint.setAntiAlias(true);
            mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            // Round Image Corner 100 100 100 100
            canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);
            imageView.setImageBitmap(imageRounded);
        }

        @Override
        public abstract void onMapReady(GoogleMap googleMap);
    }
}
