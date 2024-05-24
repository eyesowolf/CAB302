package com.example.cab302.controller;

import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.moodData;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoodChartViewController {
    @FXML
    private BarChart<String, Number> moodBarChart;

    private SqliteUsersDAO usersDAO;

    @FXML
    public void initialize() {
        usersDAO = new SqliteUsersDAO();
        populateMoodChart();
    }

    private void populateMoodChart() {
        List<moodData> moodDataList = usersDAO.getAllMoodData();
        Map<String, Long> moodCountMap = moodDataList.stream()
                .collect(Collectors.groupingBy(moodData::getEntryMood, Collectors.counting()));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Long> entry : moodCountMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        moodBarChart.getData().add(series);
    }
}