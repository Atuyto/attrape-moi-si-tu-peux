module com.example.attrape_moi_si_tu_peux {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.attrape_moi_si_tu_peux to javafx.fxml;
    exports com.example.attrape_moi_si_tu_peux;
    exports com.example.attrape_moi_si_tu_peux.view;
    opens com.example.attrape_moi_si_tu_peux.view to javafx.fxml;
}