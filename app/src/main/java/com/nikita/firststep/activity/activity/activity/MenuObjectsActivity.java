package com.nikita.firststep.activity.activity.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;

import com.nikita.firststep.activity.activity.adapter.ShopsAdapter;
import com.nikita.firststep.activity.activity.other.YaroslavlObject;

import java.util.Collections;
import java.util.List;

import nikita.myappfirststep.R;

public abstract class MenuObjectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Converting dp to pixel
     */
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    protected abstract void prepareObjects();

    /**
     * This method sorts objects on distance from obshaga
     * @param list - list of objects
     * @param adapter - shops adapter that inflates one element of list
     */
    public void sort(final List<YaroslavlObject> list, ShopsAdapter adapter) {
        if (list != null) {
            Collections.sort(list);
            adapter.notifyDataSetChanged();
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main_content), "Отсортировано" + "по удаленности",
                    Snackbar.LENGTH_LONG);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            snackbar.show();
        }
    }
}
