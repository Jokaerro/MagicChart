package tesla.andrew.magicchart.custom_views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

import tesla.andrew.magicchart.R;

/**
 * Created by TESLA on 26.07.2017.
 */

public class MagicChart extends CardView {
    private RecyclerView container;
    private Context context;
    private ChartAdapter adapter;
    private List<ColumnModel> columnModels = new LinkedList<>();
    private CheckBox newCheck;
    private CheckBox learnedCheck;
    private CheckBox repeatCheck;

    public MagicChart(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MagicChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MagicChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.chart, this);
        this.container = (RecyclerView) findViewById(R.id.container);

        this.container.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);
        this.container.setLayoutManager(llm);

        this.adapter = new ChartAdapter(context, columnModels, new ChartClickListener() {
            @Override
            public void onColumnClick(ChartAdapter.MyViewHolder column) {

            }
        });
        this.container.setAdapter(this.adapter);

        newCheck = (CheckBox) findViewById(R.id.newWords_check);
        learnedCheck = (CheckBox) findViewById(R.id.learnedWords_check);
        repeatCheck = (CheckBox) findViewById(R.id.repeatWords_check);

        newCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for(int i=0 ; i < adapter.getViewItemsCount() ; i++){
                    ChartAdapter.MyViewHolder obj = (ChartAdapter.MyViewHolder)adapter.getItem(i);
                    obj.column.showNewWords(b);
                }
            }
        });

        learnedCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for(int i=0 ; i < adapter.getViewItemsCount() ; i++){
                    ChartAdapter.MyViewHolder obj = (ChartAdapter.MyViewHolder)adapter.getItem(i);
                    obj.column.showLearnedWords(b);
                }
            }
        });

        repeatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for(int i=0 ; i < adapter.getViewItemsCount() ; i++){
                    ChartAdapter.MyViewHolder obj = (ChartAdapter.MyViewHolder)adapter.getItem(i);
                    obj.column.showRepeatWords(b);
                }
            }
        });
    }

    public void updateChart(List<ColumnModel> columns) {
        this.adapter.swapData(columns);
    }

    public interface ChartClickListener {
        void onColumnClick(ChartAdapter.MyViewHolder column);
    }
}
