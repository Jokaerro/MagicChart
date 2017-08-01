package tesla.andrew.magicchart.custom_views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
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
                if(animationQueue.peek() != column) {
                    selectedColumn = column;

                    ObjectAnimator widthItem = ObjectAnimator.ofInt(column, "widthItem", 24, 72);
                    widthItem.setDuration(200);

                    final ObjectAnimator anim = ObjectAnimator.ofFloat(column.getBackGroundCard(), "alpha", 0f, 1f);
                    anim.setDuration(200);

                    final ObjectAnimator widthColumn = ObjectAnimator.ofFloat(column.getColumnItem(), "scaleX", 1.5f);
                    widthColumn.setDuration(200);

                    widthItem.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            requestLayout();
                        }
                    });

                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            requestLayout();
                        }
                    });
                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            column.getBackGroundCard().setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            column.setVisibleBackCard(true);
                        }
                        @Override
                        public void onAnimationCancel(Animator animator) {}
                        @Override
                        public void onAnimationRepeat(Animator animator) {}
                    });

                    widthColumn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            requestLayout();
                        }
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

                    AnimatorSet expandSet = new AnimatorSet();
                    expandSet.playTogether(widthItem, widthColumn);

                    column.expandAnimation.playSequentially(expandSet, anim);
                    column.expandAnimation.start();

                    animationQueue.add(column);
                    if (animationQueue.size() > 1) {
                        Column col = animationQueue.poll();
                        deselectCurrentColumn(col);
                    }
                } else {
                    Column col = animationQueue.poll();
                    deselectCurrentColumn(col);
                    selectedColumn = null;
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
                column.hideAllCounts();
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
                column.hideAllCounts();
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
                column.hideAllCounts();
            }
        }
    }

    public void deselectCurrentColumn(final Column column) {
        if(column != null) {
            column.setSelected(false);
            column.hideAllCounts();

            final ObjectAnimator widthItem = ObjectAnimator.ofInt(column, "widthItem", 72, 24);
            widthItem.setDuration(200);

            final ObjectAnimator anim = ObjectAnimator.ofFloat(column.getBackGroundCard(), "alpha", 1f, 0f);
            anim.setDuration(200);

            ObjectAnimator widthColumn = ObjectAnimator.ofFloat(column.getColumnItem(), "scaleX", 1.0f);
            widthColumn.setDuration(200);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    column.hideAllCounts();
                }
                @Override
                public void onAnimationEnd(Animator animator) {
                    column.setVisibleBackCard(false);
                }
                @Override
                public void onAnimationCancel(Animator animator) {}
                @Override
                public void onAnimationRepeat(Animator animator) {}
            });

            widthItem.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    requestLayout();;
                }
            });

            AnimatorSet collapseSet = new AnimatorSet();
            collapseSet.playTogether(widthItem, widthColumn);

            column.colapseAnimation.playSequentially(anim, collapseSet);
            if(column.expandAnimation.isRunning()) {
                column.expandAnimation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {}
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        column.colapseAnimation.start();
                        column.expandAnimation.removeAllListeners();
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {}
                    @Override
                    public void onAnimationRepeat(Animator animator) {}
                });
            } else {
                column.colapseAnimation.start();
            }
        }
    }

}
