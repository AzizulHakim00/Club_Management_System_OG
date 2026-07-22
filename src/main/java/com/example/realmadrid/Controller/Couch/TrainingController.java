package com.example.realmadrid.Controller.Couch;

import com.example.realmadrid.Controller.Couch.TransferAllPopUp.TrainingPopupController;
import com.example.realmadrid.Model.TrainingDAO;
import com.example.realmadrid.Model.TrainingSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TrainingController implements Initializable {
    @FXML private TableView<TrainingSession> table;
    @FXML private TableColumn<TrainingSession, String> colName;
    @FXML private TableColumn<TrainingSession, LocalDate> colDate;
    @FXML private TableColumn<TrainingSession, String> colType;
    @FXML private TableColumn<TrainingSession, String> colAttendance;
    @FXML private TableColumn<TrainingSession, String> colFitness;
    @FXML private TableColumn<TrainingSession, String> colInjury;

    @FXML private TextField nameField, typeField, attendanceField, fitnessField, searchField;
    @FXML private TextArea injuryField;
    @FXML private DatePicker datePicker;
    @FXML private PieChart pieChart;
    @FXML private LineChart<String, Number> lineChart;
    @FXML private BarChart<String, Number> barChart;
    @FXML private Button exportPdfBtn, exportExcelBtn;
    @FXML private AnchorPane calendarPane;

    private final TrainingDAO dao = new TrainingDAO();
    private ObservableList<TrainingSession> masterData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        exportExcelBtn.setOnAction( event -> { exportToExcel();});
        exportPdfBtn.setOnAction(e -> onPdf());
    }
    private void onPdf() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("training_data.pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File file = fileChooser.showSaveDialog(table.getScene().getWindow());

        if (file != null) {
            try {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font headFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);

                document.add(new com.itextpdf.text.Paragraph("Real Madrid Training Sessions", titleFont));
                document.add(new com.itextpdf.text.Paragraph(" ")); // empty line

                com.itextpdf.text.pdf.PdfPTable tablePDF = new com.itextpdf.text.pdf.PdfPTable(6);
                tablePDF.setWidthPercentage(100);
                tablePDF.setWidths(new float[]{2, 2, 2, 2, 2, 3});

                tablePDF.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Player", headFont)));
                tablePDF.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Date", headFont)));
                tablePDF.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Type", headFont)));
                tablePDF.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Attendance", headFont)));
                tablePDF.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Fitness", headFont)));
                tablePDF.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Injury Notes", headFont)));

                for (TrainingSession session : masterData) {
                    tablePDF.addCell(session.getPlayerName());
                    tablePDF.addCell(session.getDate().toString());
                    tablePDF.addCell(session.getSessionType());
                    tablePDF.addCell(session.getAttendanceStatus());
                    tablePDF.addCell(session.getFitnessLevel());
                    tablePDF.addCell(session.getInjuryNotes());
                }

                document.add(tablePDF);
                document.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export Successful");
                alert.setHeaderText(null);
                alert.setContentText("PDF file saved successfully.");
                alert.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export Failed");
                alert.setHeaderText(null);
                alert.setContentText("Failed to export PDF: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }


    private void initializeController() throws SQLException {
        loadTable();
        setupSearch();
        populateCharts();
        setupCalendar();

        // Update charts when table selection changes
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                populateCharts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Update charts when search text changes
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                populateCharts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Open popup on double click on table row
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TrainingSession selected = table.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    openPopup(selected);
                }
            }
        });
    }

    private void openPopup(TrainingSession session) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/realmadrid/Couch/transferallpopup/traingpopup.fxml"));
            Parent root = loader.load();

            TrainingPopupController popupController = loader.getController();
            popupController.setSession(session);

            popupController.setRefreshCallback(() -> {
                try {
                    loadTable();
                    populateCharts();
                    setupCalendar();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(table.getScene().getWindow());
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error opening popup");
        }
    }

    private void loadTable() throws SQLException {
        masterData = FXCollections.observableArrayList(dao.getAllSessions());
        table.setItems(masterData);
        colName.setCellValueFactory(cell -> cell.getValue().playerNameProperty());
        colDate.setCellValueFactory(cell -> cell.getValue().dateProperty());
        colType.setCellValueFactory(cell -> cell.getValue().sessionTypeProperty());
        colAttendance.setCellValueFactory(cell -> cell.getValue().attendanceStatusProperty());
        colFitness.setCellValueFactory(cell -> cell.getValue().fitnessLevelProperty());
        colInjury.setCellValueFactory(cell -> cell.getValue().injuryNotesProperty());
    }

    private void setupSearch() {
        FilteredList<TrainingSession> filtered = new FilteredList<>(masterData, p -> true);
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filtered.setPredicate(session -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                return session.getPlayerName().toLowerCase().contains(newVal.toLowerCase());
            });
        });
        table.setItems(filtered);
    }

    private void populateCharts() throws SQLException {
        // Debug prints
        System.out.println("Pie counts: Cardio=" + dao.countByType("Cardio"));
        System.out.println("Selected player for line chart: " + (table.getSelectionModel().getSelectedItem() != null ? table.getSelectionModel().getSelectedItem().getPlayerName() : "Luka Modric"));
        System.out.println("Attendance present: " + dao.countByAttendance("Present"));

        // Pie Chart
        pieChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("Cardio", (int) dao.countByType("Cardio")),
                new PieChart.Data("Strength", (int) dao.countByType("Strength")),
                new PieChart.Data("Tactical", (int) dao.countByType("Tactical"))
        ));

        // Line Chart (Fitness Trend for selected or default player)
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        String playerName;
        if (table.getSelectionModel().getSelectedItem() != null) {
            playerName = table.getSelectionModel().getSelectedItem().getPlayerName();
        } else {
            playerName = "Marcelo";  // default player without special char
        }

        for (TrainingSession s : dao.getSessionsForPlayer(playerName)) {
            int score = convertFitnessToNumber(s.getFitnessLevel());
            series.getData().add(new XYChart.Data<>(s.getDate().toString(), score));
        }
        lineChart.getData().add(series);

        // Bar Chart (Attendance)
        barChart.getData().clear();
        XYChart.Series<String, Number> attendance = new XYChart.Series<>();
        attendance.getData().add(new XYChart.Data<>("Present", dao.countByAttendance("Present")));
        attendance.getData().add(new XYChart.Data<>("Absent", dao.countByAttendance("Absent")));
        barChart.getData().add(attendance);
    }

    private int convertFitnessToNumber(String level) {
        return switch (level.toLowerCase()) {
            case "high" -> 3;
            case "medium" -> 2;
            case "low" -> 1;
            default -> 0;
        };
    }

    private void setupCalendar() {
        calendarPane.getChildren().clear();

        CalendarView calendarView = new CalendarView();

        Calendar calendar = new Calendar("Training Sessions");
        calendar.setStyle(Calendar.Style.STYLE1);

        for (TrainingSession s : masterData) {
            Entry<String> entry = new Entry<>(s.getPlayerName() + " - " + s.getSessionType());
            entry.setInterval(s.getDate().atTime(9, 0));
            calendar.addEntry(entry);
        }

        CalendarSource calendarSource = new CalendarSource("Session Calendar");
        calendarSource.getCalendars().add(calendar);
        calendarView.getCalendarSources().add(calendarSource);

        calendarView.setRequestedTime(LocalTime.of(9, 0));
        calendarView.setDate(LocalDate.now());
        calendarView.showMonthPage();

        calendarPane.getChildren().add(calendarView);
        AnchorPane.setTopAnchor(calendarView, 0.0);
        AnchorPane.setBottomAnchor(calendarView, 0.0);
        AnchorPane.setLeftAnchor(calendarView, 0.0);
        AnchorPane.setRightAnchor(calendarView, 0.0);
    }

    @FXML
    private void exportToExcel() {
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Training Data");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Player");
            header.createCell(1).setCellValue("Date");
            header.createCell(2).setCellValue("Type");
            header.createCell(3).setCellValue("Attendance");
            header.createCell(4).setCellValue("Fitness");

            int rowNum = 1;
            for (TrainingSession s : masterData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getPlayerName());
                row.createCell(1).setCellValue(s.getDate().toString());
                row.createCell(2).setCellValue(s.getSessionType());
                row.createCell(3).setCellValue(s.getAttendanceStatus());
                row.createCell(4).setCellValue(s.getFitnessLevel());
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("training_data.xlsx");
            var file = fileChooser.showSaveDialog(table.getScene().getWindow());
            if (file != null) {
                try (FileOutputStream out = new FileOutputStream(file)) {
                    wb.write(out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
