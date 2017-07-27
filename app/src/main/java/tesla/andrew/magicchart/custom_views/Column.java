package tesla.andrew.magicchart.custom_views;

import org.w3c.dom.Text;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tesla.andrew.magicchart.R;

/**
 * Created by TESLA on 26.07.2017.
 */

public class Column extends LinearLayout {
    private ImageView learnedWords;
    private ImageView repeatWords;
    private ImageView newWords;
    private LinearLayout column;
    private Context context;

    private int learnedCount;
    private int repeatCount;
    private int newCount;

    private TextView learnedWordsCaption;
    private TextView repeatWordsCaption;
    private TextView newWordsCaption;

    public Column(Context context) {
        super(context);
        init(context);
    }

    public Column(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Column(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        inflate(getContext(), R.layout.column, this);
        this.learnedWords = (ImageView)findViewById(R.id.learnedWords);
        this.repeatWords = (ImageView)findViewById(R.id.repeatWords);
        this.newWords = (ImageView)findViewById(R.id.newWords);
        this.column = (LinearLayout) findViewById(R.id.column);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/SFUIDisplayMedium.ttf");
        this.learnedWordsCaption = (TextView) findViewById(R.id.learnedWordsCaption);
        this.learnedWordsCaption.setTypeface(type);

        this.repeatWordsCaption = (TextView)findViewById(R.id.repeatWordsCaption);
        this.repeatWordsCaption.setTypeface(type);

        this.newWordsCaption = (TextView)findViewById(R.id.newWordsCaption);
        this.newWordsCaption.setTypeface(type);

        this.context = context;
    }

    public void createColumn(int learnedCount, int repeatCount, int newCount, String day, String month, int width) {
        this.learnedCount = learnedCount;
        setLearnedWordsHeight(learnedCount);

        this.repeatCount = repeatCount;
        setRepeatWordsHeight(repeatCount);

        this.newCount = newCount;
        setNewWordsHeight(newCount);

        setColumnWidth(width);
    }

    public void setColumnWidth(int width) {
//        Drawable lern = getResources().getDrawable(R.drawable.learned);
//        Bitmap bitmapResized = Bitmap.createScaledBitmap(((BitmapDrawable)lern).getBitmap(), width, dpToPx(context, learnedCount), false);
////        lern.setBounds(0,0, width, dpToPx(context, learnedCount));
//        learnedWords.setImageDrawable(new BitmapDrawable(getResources(), bitmapResized));
//
//        Drawable rept = getResources().getDrawable(R.drawable.repeat);
//        Bitmap bitmapResized1 = Bitmap.createScaledBitmap(((BitmapDrawable)rept).getBitmap(), width, dpToPx(context, repeatCount), false);
//        repeatWords.setImageDrawable(new BitmapDrawable(getResources(), bitmapResized1));
////        lern.setBounds(0,0, width, dpToPx(context, repeatCount));
////        repeatWords.setImageDrawable(rept);
//
//        Drawable neww = getResources().getDrawable(R.drawable.neww);
//        Bitmap bitmapResized2 = Bitmap.createScaledBitmap(((BitmapDrawable)neww).getBitmap(), width, dpToPx(context, newCount), false);
//        newWords.setImageDrawable(new BitmapDrawable(getResources(), bitmapResized2));
//        lern.setBounds(0,0, width, dpToPx(context, newCount));
//        newWords.setImageDrawable(neww);
//        column.getLayoutParams().width = dpToPx(context, width);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, 120), 0);
//        column.setLayoutParams(layoutParams);

//        column.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

//        layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, learnedCount));
//        learnedWords.setLayoutParams(layoutParams);

//        layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, repeatCount));
//        repeatWords.setLayoutParams(layoutParams);
//
//        layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, newCount));
//        newWords.setLayoutParams(layoutParams);
    }

    public void setVisibleCounts(Boolean visible) {
        if(visible) {
            if(newCount > 9 && newWords.getVisibility() == VISIBLE) {
                newWordsCaption.setText(String.valueOf(newCount));
                newWordsCaption.setVisibility(VISIBLE);
            }
            if(repeatCount > 9 && repeatWords.getVisibility() == VISIBLE) {
                repeatWordsCaption.setText(String.valueOf(repeatCount));
                repeatWordsCaption.setVisibility(VISIBLE);
            }
            if(learnedCount > 9 && learnedWords.getVisibility() == VISIBLE) {
                learnedWordsCaption.setText(String.valueOf(learnedCount));
                learnedWordsCaption.setVisibility(VISIBLE);
            }
        } else {
            newWordsCaption.setVisibility(GONE);
            repeatWordsCaption.setVisibility(GONE);
            learnedWordsCaption.setVisibility(GONE);
        }
    }

    public void setLearnedWordsHeight(int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        learnedWords.setLayoutParams(layoutParams);
    }

    public void showLearnedWords(Boolean visible) {
        if(visible) {
            learnedWords.setVisibility(VISIBLE);
            if(learnedCount > 9)
                learnedWordsCaption.setVisibility(VISIBLE);
        } else {
            learnedWords.setVisibility(GONE);
            learnedWordsCaption.setVisibility(GONE);
        }
    }

    public void setRepeatWordsHeight(int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        repeatWords.setLayoutParams(layoutParams);
    }

    public void showRepeatWords(Boolean visible) {
        if(visible) {
            repeatWords.setVisibility(VISIBLE);
            if(repeatCount > 9)
                repeatWordsCaption.setVisibility(VISIBLE);
        } else {
            repeatWords.setVisibility(GONE);
            repeatWordsCaption.setVisibility(GONE);
        }
    }

    public void setNewWordsHeight(int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        newWords.setLayoutParams(layoutParams);
    }

    public void showNewWords(Boolean visible) {
        if(visible) {
            newWords.setVisibility(VISIBLE);
            if(newCount > 9)
                newWordsCaption.setVisibility(VISIBLE);
        } else {
            newWords.setVisibility(GONE);
            newWordsCaption.setVisibility(GONE);
        }
    }

    public int getLearnedCount() {
        return learnedCount;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public int getNewCount() {
        return newCount;
    }

    public static int dpToPx(Context context, float dp)
    {
        final float scale= context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 20.5f);
    }
}
