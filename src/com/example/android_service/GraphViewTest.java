package com.example.android_service;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class GraphViewTest extends BaseUIActivity {
	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_graphviewtest);
		layout = (LinearLayout) findViewById(R.id.mainview);
		// init example series data
		GraphViewSeries exampleSeries = new GraphViewSeries(
				new GraphViewData[] { new GraphViewData(1, 3.8d),
						new GraphViewData(2, 2.0d), new GraphViewData(3, 2.7d),
						new GraphViewData(4, 3.5d),new GraphViewData(5, 3.5d) });

		LineGraphView graphView = new LineGraphView(this // context
				, "成绩单" // heading
		);
		// graphView.setDrawBackground(true);
		// graphView.setBackgroundColor(Color.rgb(80, 30, 30));

		graphView.setDrawDataPoints(true);
		graphView.setDataPointsRadius(5f);
		graphView.getGraphViewStyle().setTextSize(16f);
		graphView.getGraphViewStyle().setNumVerticalLabels(5);
		graphView.getGraphViewStyle().setNumHorizontalLabels(8);
		graphView.addSeries(exampleSeries); // data

		layout.addView(graphView);
	}

	@Override
	protected void initLayout() {

	}

	@Override
	protected void initData() {

	}

}
