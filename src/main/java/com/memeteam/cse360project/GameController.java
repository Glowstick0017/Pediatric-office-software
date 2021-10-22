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

    public Label cpslabel;

    public Label tylenolLabel;
    public Button tylenolButton;
    public Label tylenolCount;

    public Label xanaxcount;
    public Button xanaxbutton;
    public Label xanaxlabel;

    public Label methlabel;
    public Button methbutton;
    public Label methcount;

    public int accidents;
    public int tylenolPrice = 10;
    public int xanaxPrice = 100;
    public int methPrice = 1000;

    public boolean shutdown = false;

    public void onWheelchairClick(MouseEvent mouseEvent) {
        accidents = Integer.parseInt(accidentCount.getText());
        accidentCount.setText(String.valueOf(accidents+1));
        showItems();
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

    public void onXanaxBuy(ActionEvent event) {
        accidents = Integer.parseInt(accidentCount.getText());
        xanaxPrice = Integer.parseInt(xanaxbutton.getText().replaceAll("[^0-9]", ""));
        accidents-=xanaxPrice;
        xanaxbutton.setDisable(accidents < xanaxPrice);
        accidentCount.setText(String.valueOf(accidents));
        xanaxcount.setText("x" + (Integer.parseInt(xanaxcount.getText().replaceAll("[^0-9]", "")) + 1));
        xanaxPrice += (int) Math.floor(xanaxPrice * .5);
        xanaxbutton.setText("Buy $" + xanaxPrice);
    }

    public void onMethBuy() {
        accidents = Integer.parseInt(accidentCount.getText());
        methPrice = Integer.parseInt(methbutton.getText().replaceAll("[^0-9]", ""));
        accidents-=methPrice;
        methbutton.setDisable(accidents < methPrice);
        accidentCount.setText(String.valueOf(accidents));
        methcount.setText("x" + (Integer.parseInt(methcount.getText().replaceAll("[^0-9]", "")) + 1));
        methPrice += (int) Math.floor(methPrice * .5);
        methbutton.setText("Buy $" + methPrice);

    }

    public void clicksPerSecond() {
        new Thread(() -> {
            int delay = 0;
            int period = 1000; // repeat every sec.
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        int tylenolcps = Integer.parseInt(tylenolCount.getText().replaceAll("[^0-9]", ""));
                        int xanaxcps = 2 * Integer.parseInt(xanaxcount.getText().replaceAll("[^0-9]", ""));
                        int methcps = 3 * Integer.parseInt(methcount.getText().replaceAll("[^0-9]", ""));
                        int totalcps = tylenolcps + xanaxcps + methcps;
                        for (int i = 0; i < totalcps; i++) {
                            accidents = Integer.parseInt(accidentCount.getText());
                            accidents++;
                            accidentCount.setText(String.valueOf(accidents));
                            tylenolButton.setDisable(accidents < tylenolPrice);
                            xanaxbutton.setDisable(accidents < xanaxPrice);
                            methbutton.setDisable(accidents < methPrice);
                            cpslabel.setVisible(true);
                            cpslabel.setText("CPS: " + totalcps);
                            showItems();
                        }
                        if (shutdown) {
                            Thread.currentThread().interrupt();
                            timer.cancel();
                        }
                    });
                }
            }, delay, period);
        }).start();
    }

    public void showItems() {
        if (accidents >= tylenolPrice-1) {
            tylenolButton.setVisible(true);
            tylenolCount.setVisible(true);
            tylenolLabel.setVisible(true);
        }
        if (accidents >= xanaxPrice-1) {
            xanaxbutton.setVisible(true);
            xanaxcount.setVisible(true);
            xanaxlabel.setVisible(true);
        }
        if (accidents >= methPrice-1) {
            methbutton.setVisible(true);
            methcount.setVisible(true);
            methlabel.setVisible(true);
        }
    }

    public void shutdown() {
        shutdown=true;
    }
}
