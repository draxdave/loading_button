package ir.drax.loadingbuttonSample.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.drax.loadingbutton.EventListener;
import ir.drax.loadingbutton.NormalButton;
import ir.drax.loadingbuttonSample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NormalButton normalButton= findViewById(R.id.myLoading);
        normalButton.setEventListener(new EventListener() {
            @Override
            public void clicked() {

            }

            @Override
            public void longClicked() {
                normalButton.enable();
            }
        });
    }
}
