package tesla.andrew.magicchart.custom_views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tesla.andrew.magicchart.R;

/**
 * Created by TESLA on 27.07.2017.
 */

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.MyViewHolder>  {

    private List<ColumnModel> data;
    private Context context;
    private MagicChart.ChartClickListener callback;
    private List<MyViewHolder> items = new ArrayList<>();
    private MyViewHolder currentColumn = null;

    public ChartAdapter(Context context, List<ColumnModel> data, MagicChart.ChartClickListener callback) {
        this.context = context;
        if (data != null)
            this.data = new LinkedList<>(data);
        else this.data = new LinkedList<>();
        this.callback = callback;
    }

    public void swapData(List<ColumnModel> data){
        if (data != null) {
            for (int i = 0; i < data.size(); i++)
                if (data.get(i) == null)
                    data.remove(i);
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public MyViewHolder getItem(int index) {
        return this.items.get(index);
    }

    public int getViewItemsCount() {
        return  this.items.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.column_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if(data.get(position) != null) {
            items.add(holder);
            holder.day.setText(data.get(position).getDay());
            holder.month.setText(data.get(position).getMonth());

            holder.column.createColumn(this.data.get(position).getLearnedCount(),
                    this.data.get(position).getRepeatCount(),
                    this.data.get(position).getNewCount(),
                    this.data.get(position).getDay(),
                    this.data.get(position).getMonth(),
                    24);

            holder.column.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onColumnClick(holder);

                    if(holder.backCard.getVisibility()== View.GONE) {
                        holder.backCard.setVisibility(View.VISIBLE);
                        currentColumn = holder;
//                        holder.setWidth(56);

                        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(holder.column, "scaleX", 1.5f);
                        scaleDownX.setDuration(600);
                        AnimatorSet scaleDown = new AnimatorSet();
                        scaleDown.play(scaleDownX);
                        scaleDown.start();

                        scaleDown.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                holder.column.setVisibleCounts(true);
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });


                    } else {
                        holder.backCard.setVisibility(View.GONE);

                        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(holder.column, "scaleX", 1.0f);
                        scaleDownX.setDuration(600);
                        AnimatorSet scaleDown = new AnimatorSet();
                        scaleDown.play(scaleDownX);
                        scaleDown.start();

                        currentColumn = null;
                        holder.column.setVisibleCounts(false);
                    }
                }
            });
        }
    }

    public MyViewHolder getSelectedColumn() {
        return currentColumn;
    }

    public void unselectCurrentColumn() {
        if(currentColumn != null) {
            currentColumn.backCard.setVisibility(View.GONE);

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(currentColumn.column, "scaleX", 1.0f);
            scaleDownX.setDuration(600);
            AnimatorSet scaleDown = new AnimatorSet();
            scaleDown.play(scaleDownX);
            scaleDown.start();

            currentColumn.column.setVisibleCounts(false);
            currentColumn = null;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Column column;
        public CardView backCard;
        public TextView day;
        public TextView month;

        public MyViewHolder(View itemView) {
            super(itemView);
            column = (Column) itemView.findViewById(R.id.column_item);
            backCard = (CardView) itemView.findViewById(R.id.back_card);

            Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/SFUIDisplayRegular.ttf");
            day = (TextView) itemView.findViewById(R.id.day);
            day.setTypeface(type);

            Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/SFUIDisplayMedium.ttf");
            month = (TextView) itemView.findViewById(R.id.month);
        }

        public void setWidth(int width) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dpToPx(context, width), ViewGroup.LayoutParams.WRAP_CONTENT);
            column.setLayoutParams(layoutParams);
        }

        public int dpToPx(Context context, float dp)
        {
            final float scale= context.getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
        }
    }
}
