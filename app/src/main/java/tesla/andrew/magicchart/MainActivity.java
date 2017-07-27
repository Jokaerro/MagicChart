package tesla.andrew.magicchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tesla.andrew.magicchart.custom_views.Column;
import tesla.andrew.magicchart.custom_views.ColumnModel;
import tesla.andrew.magicchart.custom_views.MagicChart;

public class MainActivity extends AppCompatActivity {
    public MagicChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart = (MagicChart) findViewById(R.id.chart);

        mockChart();
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
