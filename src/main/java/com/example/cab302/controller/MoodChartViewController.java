package com.example.cab302.controller;

import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.moodData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MoodChartViewController {

    @FXML
    private TableView<moodData> moodTableView;

    @FXML
    private TableColumn<moodData, Integer> idColumn;

    @FXML
    private TableColumn<moodData, String> dateColumn;

    @FXML
    private TableColumn<moodData, String> moodTypeColumn;

    @FXML
    private TableColumn<moodData, String> descriptionColumn;

    private SqliteUsersDAO usersDAO;
    private int currentUserId;

    @FXML
    public void initialize() {
        usersDAO = new SqliteUsersDAO();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("dataID"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        moodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("entryMood"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("entryDescription"));

        // populateMoodTable() will be called when setCurrentUserId is called
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        populateMoodTable();
    }

    private void populateMoodTable() {
        List<moodData> moodDataList = usersDAO.getMoodDataByUserId(currentUserId);
        ObservableList<moodData> moodDataObservableList = FXCollections.observableArrayList(moodDataList);
        moodTableView.setItems(moodDataObservableList);
    }
}