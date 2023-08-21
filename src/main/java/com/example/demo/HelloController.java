package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1_1, bg1_2, bg1_3, bg2_1, bg2_2, bg2_3;

    private final int BG_WIDTH = 712;

    private ParallelTransition bg1_1parallelTransition;
    private ParallelTransition bg2_2parallelTransition;
    private ParallelTransition bg3_3parallelTransition;

    @FXML
    void initialize() {

        // for front background images
        TranslateTransition bg1_3Transition = createTranslateTransition(bg1_3, 5000);
        TranslateTransition bg2_3Transition = createTranslateTransition(bg2_3, 5000);
        bg3_3parallelTransition = new ParallelTransition(bg1_3Transition, bg2_3Transition);
        bg3_3parallelTransition.setCycleCount(TranslateTransition.INDEFINITE);
        bg3_3parallelTransition.play();

        // for middle background images
        TranslateTransition bg1_2Transition = createTranslateTransition(bg1_2, 10000);
        TranslateTransition bg2_2Transition = createTranslateTransition(bg2_2, 10000);
        bg2_2parallelTransition = new ParallelTransition(bg1_2Transition, bg2_2Transition);
        bg2_2parallelTransition.setCycleCount(TranslateTransition.INDEFINITE);
        bg2_2parallelTransition.play();


        //for last background images
        TranslateTransition bg1_1Transition = createTranslateTransition(bg1_1, 20000);
        TranslateTransition bg2_1Transition = createTranslateTransition(bg2_1, 20000);
        bg1_1parallelTransition = new ParallelTransition(bg1_1Transition, bg2_1Transition);
        bg1_1parallelTransition.setCycleCount(TranslateTransition.INDEFINITE);
        bg1_1parallelTransition.play();
    }

    private TranslateTransition createTranslateTransition(ImageView imageView, int durationMillis) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(durationMillis), imageView);
        transition.setFromX(0);
        transition.setToX(BG_WIDTH * -1);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        return transition;
    }
}
