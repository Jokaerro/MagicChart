package tesla.andrew.magicchart.custom_views;

import org.w3c.dom.Text;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private TextView day;
    private TextView month;

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
        this.day = (TextView) this.column.findViewById(R.id.day);
        this.month = (TextView) this.column.findViewById(R.id.month);

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

        setColumnWidth(24);
    }

    public void setColumnWidth(int width) {
//        column.getLayoutParams().width = dpToPx(context, width);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, 120), 0);
        column.setLayoutParams(layoutParams);

//        column.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

//        layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, learnedCount));
//        learnedWords.setLayoutParams(layoutParams);
//
//        layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, repeatCount));
//        repeatWords.setLayoutParams(layoutParams);
//
//        layoutParams = new LinearLayout.LayoutParams(dpToPx(context, width), dpToPx(context, newCount));
//        newWords.setLayoutParams(layoutParams);
    }

    public void setLearnedWordsHeight(int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        learnedWords.setLayoutParams(layoutParams);
    }

    public void showLearnedWords(Boolean visible) {
        if(visible)
            learnedWords.setVisibility(VISIBLE);
        else
            learnedWords.setVisibility(GONE);
    }

    public void setRepeatWordsHeight(int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        repeatWords.setLayoutParams(layoutParams);
    }

    public void showRepeatWords(Boolean visible) {
        if(visible)
            repeatWords.setVisibility(VISIBLE);
        else
            repeatWords.setVisibility(GONE);
    }

    public void setNewWordsHeight(int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(context, 24), dpToPx(context, height));
        newWords.setLayoutParams(layoutParams);
    }

    public void showNewWords(Boolean visible) {
        if(visible)
            newWords.setVisibility(VISIBLE);
        else
            newWords.setVisibility(GONE);
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
        return (int) (dp * scale + 0.5f);
    }
}
