package com.memeteam.cse360project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    public Label accidentCount;
    public Label tylenolLabel;
    public Button tylenolButton;
    public Label tylenolCount;

    public int accidents;
    public int tylenolPrice = 10;

    public void onWheelchairClick(MouseEvent mouseEvent) {
        accidents = Integer.parseInt(accidentCount.getText());
        accidentCount.setText(String.valueOf(accidents+1));
        if (accidents == tylenolPrice) {
            tylenolButton.setVisible(true);
            tylenolCount.setVisible(true);
            tylenolLabel.setVisible(true);
        }
        tylenolButton.setDisable(accidents < tylenolPrice);
    }

    public void onTylenolBuy(ActionEvent event) {
        accidents = Integer.parseInt(accidentCount.getText());
        tylenolPrice = Integer.parseInt(tylenolButton.getText().replaceAll("[^0-9]", ""));
        accidents-=tylenolPrice;
        tylenolButton.setDisable(accidents < tylenolPrice);
        accidentCount.setText(String.valueOf(accidents));
        tylenolCount.setText("x" + (Integer.parseInt(tylenolCount.getText().replaceAll("[^0-9]", "")) + 1));
        tylenolPrice += (int) Math.floor(tylenolPrice * .5);
        tylenolButton.setText("Buy $" + tylenolPrice);
    }

    public void clicksPerSecond() {
        new Thread(() -> {
            int delay = 0;
            int period = 1000; // repeat every sec.
            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        for (int i = 0; i < Integer.parseInt(tylenolCount.getText().replaceAll("[^0-9]", "")); i++) {
                            accidents = Integer.parseInt(accidentCount.getText());
                            accidents++;
                            accidentCount.setText(String.valueOf(accidents));
                            tylenolButton.setDisable(accidents < tylenolPrice);
                        }
                    });
                }
            }, delay, period);
        }).start();
    }
}
