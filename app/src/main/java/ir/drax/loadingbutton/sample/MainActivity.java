package ir.drax.loadingbutton.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ir.drax.loadingbutton.EventListener;
import ir.drax.loadingbutton.NormalButton;
import ir.drax.loadingbutton.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NormalButton normalButton= findViewById(R.id.myLoading);
        normalButton.setCircularLoading(false);
        normalButton.setEventListener(new EventListener() {
            @Override
            public void clicked() {

            }

            @Override
            public void longClicked() {
                //normalButton.enable();
            }
        });
    }
}
