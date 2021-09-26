package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicalController {

    public RadioButton ardsRadio;
    public RadioButton anginaRadio;
    public RadioButton anxietyRadio;
    public RadioButton arthritisRadio;
    public RadioButton asthmaRadio;
    public RadioButton copdRadio;
    public RadioButton chfRadio;
    public RadioButton dddRadio;
    public RadioButton depressionRadio;
    public RadioButton diabetesRadio;
    public RadioButton emphysemaRadio;
    public RadioButton hearingRadio;
    public RadioButton heartRadio;
    public RadioButton msRadio;
    public RadioButton osteoRadio;
    public RadioButton pdRadio;
    public RadioButton pvdRadio;
    public RadioButton strokeRadio;
    public RadioButton ugdRadio;
    public RadioButton viRadio;
    public CheckBox vaxxRadio;

    public static String medCombo = "000000000000000000000";
    public Button submitButton;

    public void onSubmitButtonClick(ActionEvent event) {
        RegisterController.setMedCombo(medCombo);
        PatientController.setUserMedCombo(medCombo);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void setMedCombo(String medCombo) {
        MedicalController.medCombo = medCombo;
    }

    public void predefine(String medCombo) {
        if (medCombo.charAt(0) == '1')
            ardsRadio.setSelected(true);
        if (medCombo.charAt(1) == '1')
            anginaRadio.setSelected(true);
        if (medCombo.charAt(2) == '1')
            anxietyRadio.setSelected(true);
        if (medCombo.charAt(3) == '1')
            arthritisRadio.setSelected(true);
        if (medCombo.charAt(4) == '1')
            asthmaRadio.setSelected(true);
        if (medCombo.charAt(5) == '1')
            copdRadio.setSelected(true);
        if (medCombo.charAt(6) == '1')
            chfRadio.setSelected(true);
        if (medCombo.charAt(7) == '1')
            dddRadio.setSelected(true);
        if (medCombo.charAt(8) == '1')
            depressionRadio.setSelected(true);
        if (medCombo.charAt(9) == '1')
            diabetesRadio.setSelected(true);
        if (medCombo.charAt(10) == '1')
            emphysemaRadio.setSelected(true);
        if (medCombo.charAt(11) == '1')
            hearingRadio.setSelected(true);
        if (medCombo.charAt(12) == '1')
            heartRadio.setSelected(true);
        if (medCombo.charAt(13) == '1')
            msRadio.setSelected(true);
        if (medCombo.charAt(14) == '1')
            osteoRadio.setSelected(true);
        if (medCombo.charAt(15) == '1')
            pdRadio.setSelected(true);
        if (medCombo.charAt(16) == '1')
            pvdRadio.setSelected(true);
        if (medCombo.charAt(17) == '1')
            strokeRadio.setSelected(true);
        if (medCombo.charAt(18) == '1')
            ugdRadio.setSelected(true);
        if (medCombo.charAt(19) == '1')
            viRadio.setSelected(true);
        if (medCombo.charAt(20) == '1')
            vaxxRadio.setSelected(true);
    }

    public void onMedAddClick(ActionEvent event) {
        modifyMedCombo(ardsRadio.isSelected(),0);
        modifyMedCombo(anginaRadio.isSelected(),1);
        modifyMedCombo(anxietyRadio.isSelected(),2);
        modifyMedCombo(arthritisRadio.isSelected(),3);
        modifyMedCombo(asthmaRadio.isSelected(),4);
        modifyMedCombo(copdRadio.isSelected(),5);
        modifyMedCombo(chfRadio.isSelected(),6);
        modifyMedCombo(dddRadio.isSelected(),7);
        modifyMedCombo(depressionRadio.isSelected(),8);
        modifyMedCombo(diabetesRadio.isSelected(),9);
        modifyMedCombo(emphysemaRadio.isSelected(),10);
        modifyMedCombo(hearingRadio.isSelected(),11);
        modifyMedCombo(heartRadio.isSelected(),12);
        modifyMedCombo(msRadio.isSelected(),13);
        modifyMedCombo(osteoRadio.isSelected(),14);
        modifyMedCombo(pdRadio.isSelected(),15);
        modifyMedCombo(pvdRadio.isSelected(),16);
        modifyMedCombo(strokeRadio.isSelected(),17);
        modifyMedCombo(ugdRadio.isSelected(),18);
        modifyMedCombo(viRadio.isSelected(),19);
        modifyMedCombo(vaxxRadio.isSelected(),20);
    }

    public void modifyMedCombo(Boolean enable, int index) {
        if (enable) {
            medCombo = medCombo.substring(0, index) + '1'
                    + medCombo.substring(index + 1);
        } else {
            medCombo = medCombo.substring(0, index) + '0'
                    + medCombo.substring(index + 1);
        }
    }
}
