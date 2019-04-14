package ir.drax.loadingbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NormalButton extends ConstraintLayout implements View.OnClickListener {
    private ContentLoadingProgressBar progressBar;
    private TextView title;
    private float ALPHA_DIS = 0.5f;
    private boolean isEnabled = true;


    public NormalButton(Context context) {
        super(context);
        init();
    }

    public NormalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        syncAttrs(context,attrs);
        init();
    }

    public NormalButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        syncAttrs(context,attrs);
        init();
    }

    private void syncAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.NormalButton, 0, 0);
        isEnabled = a.getBoolean(R.styleable.NormalButton_enabled,true);
        a.recycle();

    }

    public void init(){
        setOnClickListener(this);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.normal_button_main, this, true);

        progressBar=findViewById(R.id.loading_button_loading);
        title=findViewById(R.id.title);

        if (isEnabled)enable(true);
        else disable(true);
    }

    public void enable(){enable(false);}
    public void enable(boolean forced){
        if (isEnabled && !forced)return;
        else isEnabled=true;

        progressBar.hide();
        title.setAlpha(1);
    }
    public void disable(){disable(false);}
    public void disable(boolean forced){
        if (isEnabled || forced)isEnabled=false;
        else return;

        progressBar.show();
        title.setAlpha(ALPHA_DIS);
    }

    @Override
    public void onClick(View v) {

        disable();
    }
}
