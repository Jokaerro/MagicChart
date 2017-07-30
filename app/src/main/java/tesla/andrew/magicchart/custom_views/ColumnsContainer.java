package tesla.andrew.magicchart.custom_views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import tesla.andrew.magicchart.R;

/**
 * Created by TESLA on 30.07.2017.
 */

public class ColumnsContainer extends LinearLayout {
    private List<Column> mColumns = new ArrayList<>();
    private Column selectedColumn = null;
    private Context mContext;

    private Boolean showLearned = true;
    private Boolean showRepaeted = true;
    private Boolean showNewWords = true;

    public ColumnsContainer(Context context) {
        super(context);
        init(context);
    }

    public ColumnsContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ColumnsContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    public void addItem(final Column column) {
        mColumns.add(column);
        this.addView(column);
        column.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedColumn == column) {
                    deselectCurrentColumn();
                    column.setSelected(false);
//                    column.setVisibleCounts(false);
//                    column.setVisibleBackCard(false);

//                    column.setWidth(12);

                    ObjectAnimator widthColumn = ObjectAnimator.ofFloat(column, "scaleX", 1.0f);
                    widthColumn.setDuration(600);
                    AnimatorSet scaleDown = new AnimatorSet();
                    scaleDown.play(widthColumn);
                    scaleDown.start();

                    scaleDown.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            column.setVisibleCounts(false);
                            column.setVisibleBackCard(false);
//                            column.setWidth(12);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                } else {
                    deselectCurrentColumn();
                    column.setSelected(true);
                    selectedColumn = column;

//                    column.setWidth(40);
//                    column.setVisibleBackCard(true);

                    ObjectAnimator widthColumn = ObjectAnimator.ofFloat(column, "scaleX", 1.5f);
                    widthColumn.setDuration(600);
                    AnimatorSet scaleDown = new AnimatorSet();
                    scaleDown.play(widthColumn);
                    scaleDown.start();

                    scaleDown.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            column.setVisibleCounts(true);
//                            column.setWidth(40);
                            column.setVisibleBackCard(true);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            }
        });
    }

    public void addAllItems(List<Column> columns) {
        for(Column col : columns)
            addItem(col);
    }

    public void deleteItem(int i) {
        mColumns.remove(i);
        this.removeViewAt(i);
    }

    public Column getItem(int i) {
        return mColumns.get(i);
//        return this.getItem(i);
    }

    public int getItemCount() {
        return mColumns.size();
    }

    public void hideLearnedLayer(boolean visible) {
        showLearned = visible;
        for(int i = 0; i < this.getChildCount(); i++) {
            Column column = (Column) this.getChildAt(i);
            column.showLearnedWords(visible);
            if(selectedColumn == column) {
                column.setVisibleCounts(visible);
            } else {
                column.setVisibleCounts(false);
            }
        }
    }

    public void hideRepeatLayer(boolean visible) {
        showRepaeted = visible;
        for(int i = 0; i < this.getChildCount(); i++) {
            Column column = (Column) this.getChildAt(i);
            column.showRepeatWords(visible);
            if(selectedColumn == column) {
                column.setVisibleCounts(visible);
            } else {
                column.setVisibleCounts(false);
            }
        }
    }

    public void hideNewLayer(boolean visible) {
        showNewWords = visible;
        for(int i = 0; i < this.getChildCount(); i++) {
            Column column = (Column) this.getChildAt(i);
            column.showNewWords(visible);
            if(selectedColumn == column) {
                column.setVisibleCounts(visible);
            } else {
                column.setVisibleCounts(false);
            }
        }
    }

    public void deselectCurrentColumn() {
        if(selectedColumn != null) {
            selectedColumn.setVisibleBackCard(false);

            selectedColumn.setWidth(12);

            selectedColumn.setVisibleCounts(false);

        }
    }

}
