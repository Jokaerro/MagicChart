package tesla.andrew.magicchart.custom_views;

import com.bluelinelabs.conductor.Controller;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tesla.andrew.magicchart.R;

/**
 * Created by TESLA on 31.07.2017.
 */

public class ChartController extends Controller {
    public MagicChart chart;
    private TextView okButton;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.chart_screen, container, false);

        chart = (MagicChart) view.findViewById(R.id.chart);

        okButton = (TextView) view.findViewById(R.id.okButton);
        Typeface type2 = Typeface.createFromAsset(view.getResources().getAssets(),"fonts/SFUIDisplayMedium.ttf");
        okButton.setTypeface(type2);

        mockChart();

        return view;
    }

    private void mockChart() {
        ColumnModel col1 = new ColumnModel(10, 20, 40, "27", "МАЙ");
        ColumnModel col2 = new ColumnModel(1, 40, 20, "28", "МАЙ");
        ColumnModel col3 = new ColumnModel(40, 2, 31, "29", "МАЙ");
        ColumnModel col4 = new ColumnModel(1, 2, 3, "31", "МАЙ");
        ColumnModel col5 = new ColumnModel(57, 21, 12, "01", "ИЮН");
        ColumnModel col6 = new ColumnModel(25, 2, 2, "04", "ИЮН");
        ColumnModel col7 = new ColumnModel(3, 4, 86, "06", "ИЮН");

        ColumnModel col8 = new ColumnModel(10, 20, 40, "07", "ИЮН");
        ColumnModel col9 = new ColumnModel(1, 40, 20, "09", "ИЮН");
        ColumnModel col10 = new ColumnModel(40, 2, 31, "11", "ИЮН");
        ColumnModel col11 = new ColumnModel(1, 2, 3, "12", "ИЮН");
        ColumnModel col12 = new ColumnModel(57, 21, 12, "13", "ИЮН");
        ColumnModel col13 = new ColumnModel(25, 2, 2, "14", "ИЮН");
        ColumnModel col14 = new ColumnModel(3, 4, 86, "15", "ИЮН");

        List<ColumnModel> mockList1 = new LinkedList<>();
        mockList1.addAll(Arrays.asList(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14));

        chart.updateChart(mockList1);
    }
}
