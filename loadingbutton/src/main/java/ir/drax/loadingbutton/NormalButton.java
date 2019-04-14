package ir.drax.loadingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.ImageViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NormalButton extends ConstraintLayout implements View.OnLongClickListener, View.OnClickListener {
    private ContentLoadingProgressBar progressBar;
    private TextView titleTV;
    private ImageView iconTV;
    private float ALPHA_DIS = 0.5f;
    private boolean isEnabled = true ;
    private boolean circularLoading = true;
    private String title = "";
    private Drawable icon ;
    private int textColor=0,bgColor=0,progressColor=0;
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

        title = a.getString(R.styleable.NormalButton_text);

        Drawable drawable = a.getDrawable(R.styleable.NormalButton_src);
        if (drawable != null)
            icon = drawable;

        textColor=a.getColor(R.styleable.NormalButton_text_color,getResources().getColor(R.color.colorAccent));
        bgColor=a.getColor(R.styleable.NormalButton_background_color,getResources().getColor(R.color.colorPrimary));
        progressColor=a.getColor(R.styleable.NormalButton_loading_color,getResources().getColor(R.color.colorAccent));

        a.recycle();

    }

    public void init(){
        setOnClickListener(this);
        setOnLongClickListener(this);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.normal_button_main, this, true);
        setBackgroundColor(bgColor);


        setCircularLoading(circularLoading);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, progressColor));

        titleTV = findViewById(R.id.title);
        iconTV = findViewById(R.id.icon);
        setTitle(title);
        setIcon(icon);


        if (isEnabled)enable(true);
        else disable(true);
    }

    public void enable(){enable(false);}
    public void enable(boolean forced){
        if (isEnabled && !forced)return;
        else isEnabled=true;

        progressBar.hide();
        titleTV.setAlpha(1f);
        iconTV.setAlpha(1f);
    }
    public void disable(){disable(false);}
    public void disable(boolean forced){
        if (isEnabled || forced)isEnabled=false;
        else return;

        progressBar.show();
        titleTV.setAlpha(ALPHA_DIS);
        iconTV.setAlpha(ALPHA_DIS);
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

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void setCircularLoading(boolean circularLoading) {
        this.circularLoading = circularLoading;
        if (circularLoading)
            progressBar = findViewById(R.id.loading_button_circular_loading);
        else
            progressBar = findViewById(R.id.loading_button_horizontal_loading);
    }

    public void setTitle(String title) {
        this.title = title;
        titleTV.setText(title);
        titleTV.setTextColor(textColor);
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
        if (icon == null)iconTV.setVisibility(GONE);
        else
        iconTV.setImageDrawable(icon);
        ImageViewCompat.setImageTintList(iconTV, ColorStateList.valueOf(textColor));
    }
}
