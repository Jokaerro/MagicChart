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
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
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
    private RelativeLayout column;
    private Context context;

    private CardView backGroundCard;
    private boolean selected = false;

    private int learnedCount;
    private int repeatCount;
    private int newCount;

    private TextView learnedWordsCaption;
    private TextView repeatWordsCaption;
    private TextView newWordsCaption;

    private TextView day;
    private TextView month;

    private boolean learnedVisible = true;
    private boolean newVisible = true;
    private boolean repeatVisible = true;

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
        this.backGroundCard = (CardView) findViewById(R.id.back_card);

        this.column = (RelativeLayout) findViewById(R.id.column);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/SFUIDisplayMedium.ttf");
        this.learnedWordsCaption = (TextView) findViewById(R.id.learnedWordsCaption);
        this.learnedWordsCaption.setTypeface(type);

        this.repeatWordsCaption = (TextView)findViewById(R.id.repeatWordsCaption);
        this.repeatWordsCaption.setTypeface(type);

        this.newWordsCaption = (TextView)findViewById(R.id.newWordsCaption);
        this.newWordsCaption.setTypeface(type);

        Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/SFUIDisplayRegular.ttf");
        this.day = (TextView)findViewById(R.id.day);
        this.day.setTypeface(type2);

        this.month = (TextView)findViewById(R.id.month);
        this.month.setTypeface(type);

        this.context = context;
    }

    public void createColumn(int learnedCount, int repeatCount, int newCount, String day, String month) {
        this.learnedCount = learnedCount;
        setLearnedWordsHeight(learnedCount);

        this.repeatCount = repeatCount;
        setRepeatWordsHeight(repeatCount);

        this.newCount = newCount;
        setNewWordsHeight(newCount);

        this.day.setText(day);
        this.month.setText(month);

        setWidth(12);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() {
        return this.selected;
    }

    public void setWidth(int width) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(dpToPx(context, 4), 0 , dpToPx(context, 4), 0);
        column.setLayoutParams(layoutParams);
    }

    public void setVisibleBackCard(boolean visible) {
        if(visible) {
            backGroundCard.setVisibility(VISIBLE);
        } else {
            backGroundCard.setVisibility(GONE);
        }
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
        learnedVisible = visible;
        if(visible) {
            learnedWords.setVisibility(VISIBLE);
            if(learnedCount > 9)
                learnedWordsCaption.setVisibility(VISIBLE);
        } else {
            learnedWords.setVisibility(GONE);
            learnedWordsCaption.setVisibility(GONE);
        }
        manageCorners();
    }

    public void setRepeatWordsHeight(int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        repeatWords.setLayoutParams(layoutParams);
    }

    public void showRepeatWords(Boolean visible) {
        repeatVisible = visible;
        if(visible) {
            repeatWords.setVisibility(VISIBLE);
            if(repeatCount > 9)
                repeatWordsCaption.setVisibility(VISIBLE);
        } else {
            repeatWords.setVisibility(GONE);
            repeatWordsCaption.setVisibility(GONE);
        }
        manageCorners();
    }

    public void setNewWordsHeight(int height) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        newWords.setLayoutParams(layoutParams);
    }

    public void showNewWords(Boolean visible) {
        newVisible = visible;
        if(visible) {
            newWords.setVisibility(VISIBLE);
            if(newCount > 9)
                newWordsCaption.setVisibility(VISIBLE);
        } else {
            newWords.setVisibility(GONE);
            newWordsCaption.setVisibility(GONE);
        }
        manageCorners();
    }

    private void manageCorners() {
        if(!learnedVisible)
            repeatWords.setBackgroundResource(R.drawable.repeat_top);
        if(!newVisible)
            repeatWords.setBackgroundResource(R.drawable.repeat_bottom);
        if(!learnedVisible && !newVisible)
            repeatWords.setBackgroundResource(R.drawable.repeat_single);
        if(learnedVisible && newVisible)
            repeatWords.setBackgroundResource(R.drawable.repeat);

        if(!repeatVisible && !newVisible)
            learnedWords.setBackgroundResource(R.drawable.learned_single);
        else
            learnedWords.setBackgroundResource(R.drawable.learned);

        if(!repeatVisible && !learnedVisible)
            newWords.setBackgroundResource(R.drawable.neww_single);
        else
            newWords.setBackgroundResource(R.drawable.neww);
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
