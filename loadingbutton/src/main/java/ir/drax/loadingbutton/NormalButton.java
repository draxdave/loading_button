package ir.drax.loadingbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class NormalButton extends ConstraintLayout implements View.OnLongClickListener, View.OnClickListener {
    private ContentLoadingProgressBar progressBar;
    private TextView title;
    private float ALPHA_DIS = 0.5f;
    private boolean isEnabled = true;
    private boolean circularLoading = true;
    private EventListener eventListener =new EventListener() {
        @Override
        public void clicked() {

        }

        @Override
        public void longClicked() {

        }
    };


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

        circularLoading = a.getBoolean(R.styleable.NormalButton_circular_loading,true);

        a.recycle();

    }

    public void init(){
        setOnClickListener(this);
        setOnLongClickListener(this);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.normal_button_main, this, true);

        setCircularLoading(circularLoading);
        progressBar.setVisibility(View.VISIBLE);

        title = findViewById(R.id.title);

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
    public boolean onLongClick(View v) {
        disable();
        eventListener.longClicked();
        return true;
    }

    @Override
    public void onClick(View v) {
        eventListener.clicked();
        disable();
    }

    public void setEventListener(EventListener eventLinstener) {
        this.eventListener = eventLinstener;
    }

    public void setCircularLoading(boolean circularLoading) {
        this.circularLoading = circularLoading;
        if (circularLoading)
            progressBar = findViewById(R.id.loading_button_circular_loading);
        else
            progressBar = findViewById(R.id.loading_button_horizontal_loading);
    }
}
