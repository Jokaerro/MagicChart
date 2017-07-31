package tesla.andrew.magicchart.custom_views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TESLA on 30.07.2017.
 */

public class ColumnsContainer extends LinearLayout {
    private List<Column> mColumns = new ArrayList<>();
    private Column selectedColumn = null;
    private Context mContext;
    private Queue<Column> animationQueue = new LinkedBlockingQueue<>();

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
                column.setSelected(true);
                column.setWidth(40);

                column.getBackGroundCard().setVisibility(View.INVISIBLE);
                ObjectAnimator anim = ObjectAnimator.ofFloat(column.getBackGroundCard(), "alpha", 0f, 1f);
                anim.setDuration(600);
                column.setVisibleBackCard(true);

                final ObjectAnimator widthColumn = ObjectAnimator.ofFloat(column.getColumnItem(), "scaleX", 1.5f);
                widthColumn.setDuration(600);


                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {}
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        widthColumn.start();
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {}
                    @Override
                    public void onAnimationRepeat(Animator animator) {}
                });


                widthColumn.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {}
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        column.setVisibleCounts(true);
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {}
                    @Override
                    public void onAnimationRepeat(Animator animator) {}
                });

                animationQueue.add(column);
                if(animationQueue.size() > 1) {
                    Column col = animationQueue.poll();
                    deselectCurrentColumn(col);
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

    public void deselectCurrentColumn(final Column column) {
        if(column != null) {
            column.setSelected(false);
            column.setVisibleCounts(false);

            final ObjectAnimator anim = ObjectAnimator.ofFloat(column.getBackGroundCard(), "alpha", 1f, 0f);
            anim.setDuration(600);

            ObjectAnimator widthColumn = ObjectAnimator.ofFloat(column.getColumnItem(), "scaleX", 1.0f);
            widthColumn.setDuration(600);
            widthColumn.start();

            widthColumn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {}
                @Override
                public void onAnimationEnd(Animator animator) {
                    anim.start();
                }
                @Override
                public void onAnimationCancel(Animator animator) {}
                @Override
                public void onAnimationRepeat(Animator animator) {}
            });

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {}
                @Override
                public void onAnimationEnd(Animator animator) {
                    column.setWidth(12);
                    column.setVisibleBackCard(false);
                    column.setVisibleCounts(false);
//                    selectedColumn = null;
                }
                @Override
                public void onAnimationCancel(Animator animator) {}
                @Override
                public void onAnimationRepeat(Animator animator) {}
            });
        }
    }

}
