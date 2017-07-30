package tesla.andrew.magicchart.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

import tesla.andrew.magicchart.R;

/**
 * Created by TESLA on 26.07.2017.
 */

public class MagicChart extends FrameLayout {
    private Context context;
    private CheckBox newCheck;
    private CheckBox learnedCheck;
    private CheckBox repeatCheck;
    private ColumnsContainer mColumnsContainer;

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
        mColumnsContainer = (ColumnsContainer) findViewById(R.id.columnsContainer);

        Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/SFUIDisplayRegular.ttf");
        newCheck = (CheckBox) findViewById(R.id.newWords_check);
        newCheck.setTypeface(type2);
        learnedCheck = (CheckBox) findViewById(R.id.learnedWords_check);
        learnedCheck.setTypeface(type2);
        repeatCheck = (CheckBox) findViewById(R.id.repeatWords_check);
        repeatCheck.setTypeface(type2);

        newCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColumnsContainer.hideNewLayer(b);
            }
        });

        learnedCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColumnsContainer.hideLearnedLayer(b);
            }
        });

        repeatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColumnsContainer.hideRepeatLayer(b);
            }
        });
    }

    public void updateChart(List<ColumnModel> columnModels) {
        for(ColumnModel columnModel : columnModels) {
            Column yetAnotherColumn = new Column(context);
            // int learnedCount, int repeatCount, int newCount, String day, String month
            yetAnotherColumn.createColumn(columnModel.getLearnedCount(), columnModel.getRepeatCount(),
                    columnModel.getNewCount(), columnModel.getDay(), columnModel.getMonth());
            mColumnsContainer.addItem(yetAnotherColumn);
        }
    }
}
