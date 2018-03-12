package com.ml.mlexamen.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Button;

import com.ml.mlexamen.BuildConfig;
import com.ml.mlexamen.R;
import com.ml.mlexamen.creditcard.CreditCardActivity;
import com.ml.mlexamen.main.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Juan on 3/12/2018.
 */



@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class , sdk = 21)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(mainActivity);
    }

    @Test
    public void checkValidationButtons() throws Exception {
        TextInputLayout amount = mainActivity.findViewById(R.id.amount);
        Button accept = mainActivity.findViewById(R.id.btn_accept);
        Button cancel = mainActivity.findViewById(R.id.btn_cancel);
        if(!TextUtils.isEmpty(amount.getEditText().getText())){
            assertEquals(accept.isEnabled(), true);
            assertEquals(cancel.isEnabled(), true);
        }else{
            assertEquals(accept.isEnabled(), false);
            assertEquals(cancel.isEnabled(), false);
        }
    }

    @Test
    public void checkCancelActionButton() throws Exception {
        TextInputLayout amount = mainActivity.findViewById(R.id.amount);
        Button cancel = mainActivity.findViewById(R.id.btn_cancel);
        amount.getEditText().setText("1000");
        cancel.performClick();
        assertEquals("", amount.getEditText().getText().toString());

    }

    @Test
    public void checkNavigationActivity() throws Exception {
        TextInputLayout amount = mainActivity.findViewById(R.id.amount);
        Button accept = mainActivity.findViewById(R.id.btn_accept);
        amount.getEditText().setText("1000");
        accept.performClick();
        Intent intent = Shadows.shadowOf(mainActivity).peekNextStartedActivity();
        assertEquals(CreditCardActivity.class.getCanonicalName(), intent.getComponent().getClassName());

    }

}
