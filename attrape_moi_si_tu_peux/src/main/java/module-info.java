module com.example.attrape_moi_si_tu_peux {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens com.example.attrape_moi_si_tu_peux.Test to javafx.fxml, org.junit.platform.commons;

    exports com.example.attrape_moi_si_tu_peux.Model;
    exports com.example.attrape_moi_si_tu_peux.view;
    opens com.example.attrape_moi_si_tu_peux.view to javafx.fxml;

}