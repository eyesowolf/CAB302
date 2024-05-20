package com.example.cab302;

import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.moodData;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class DataVisualizationController {

    @FXML
    private LineChart<String, Number> lineChart;

    private SqliteUsersDAO usersDAO = new SqliteUsersDAO();

    public void loadData() {
        List<moodData> moodDataList = usersDAO.getMoodDataForVisualization();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (moodData mood : moodDataList) {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(mood.getEntryDate());
            int moodValue = mapMoodToValue(mood.getEntryMood());
            series.getData().add(new XYChart.Data<>(date, moodValue));
        }

        lineChart.getData().add(series);
    }

    private int mapMoodToValue(String moodType) {
        switch (moodType.toLowerCase()) {
            case "happy":
                return 5;
            case "good":
                return 4;
            case "neutral":
                return 3;
            case "bad":
                return 2;
            case "sad":
                return 1;
            default:
                return 0;
        }
    }
}