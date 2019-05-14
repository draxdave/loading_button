package ir.drax.loadingbuttonSample.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ir.drax.loadingbutton.LongClickListener;
import ir.drax.loadingbutton.NormalButton;
import ir.drax.loadingbuttonSample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NormalButton normalButton= findViewById(R.id.myLoading);

        normalButton.setLongClickListener(new LongClickListener() {
            @Override
            public void longClicked(NormalButton button) {
                button.ready();
            }
        });

    }

    public void clicked(View view) {
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
    }
}
