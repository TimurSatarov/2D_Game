package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1_1, bg1_2, bg1_3, bg2_1, bg2_2, bg2_3, player, skeleton, player_run;

    @FXML
    private Label label_pause, label_lose;

    private final int BG_WIDTH = 712;



    private ParallelTransition bg1_1parallelTransition;
    private ParallelTransition bg2_2parallelTransition;
    private ParallelTransition bg3_3parallelTransition;

    TranslateTransition skeletonTransition;

    public static boolean right = false;
    public static boolean left = false;

    public static boolean jump = false;

    public static boolean isPause = false;


    private int playerSpeed = 3, jumpDownSpeed = 4;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {

            //Dir - Y (Jump)
            if(jump && player.getLayoutY() > 160f && player.getLayoutY() > 160f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if (player.getLayoutY() <= 238f && player.getLayoutY() < 238f) {
                jump = false;
                player.setLayoutY((player.getLayoutY() + jumpDownSpeed));
            }

            //Dir - X
            if(right && player_run.getLayoutX() <300f) {
                player_run.setLayoutX(player_run.getLayoutX() + playerSpeed);
            }
            if(left && player_run.getLayoutX() > -5f)
                player_run.setLayoutX(player_run.getLayoutX() - playerSpeed);


            //Pause
            if(isPause && !label_pause.isVisible()) {
                label_lose.setVisible(true);
                jumpDownSpeed = 0;
                playerSpeed = 0;
                bg1_1parallelTransition.pause();
                bg2_2parallelTransition.pause();
                bg3_3parallelTransition.pause();
                skeletonTransition.pause();
                label_pause.setVisible(true);
            }

            else if(!isPause && label_pause.isVisible()) {
                bg1_1parallelTransition.play();
                bg2_2parallelTransition.play();
                bg3_3parallelTransition.play();
                playerSpeed = 0;
                skeletonTransition.play();
                label_pause.setVisible(false);
            }

            if(player.getBoundsInParent().intersects(skeleton.getBoundsInParent())){
                jumpDownSpeed = 0;
                bg1_1parallelTransition.pause();
                bg2_2parallelTransition.pause();
                bg3_3parallelTransition.pause();
                skeletonTransition.pause();
            }
            if(jump){
                right = false;
                left = false;
            }
            if(right || left){
                player.setVisible(false);
                player_run.setVisible(true);
                bg1_1parallelTransition.play();
                bg2_2parallelTransition.play();
                bg3_3parallelTransition.play();
            }else{
                player.setVisible(true);
                player_run.setVisible(false);
                bg1_1parallelTransition.pause();
                bg2_2parallelTransition.pause();
                bg3_3parallelTransition.pause();
            }

        }
    };

    @FXML
    void initialize() {

//        if(right || left){
        // for front background images
        TranslateTransition bg1_3Transition = createTranslateTransition(bg1_3, 10000);
        TranslateTransition bg2_3Transition = createTranslateTransition(bg2_3, 10000);
        bg3_3parallelTransition = new ParallelTransition(bg1_3Transition, bg2_3Transition);
        bg3_3parallelTransition.setCycleCount(TranslateTransition.INDEFINITE);
        bg3_3parallelTransition.play();

        // for middle background images
        TranslateTransition bg1_2Transition = createTranslateTransition(bg1_2, 15000);
        TranslateTransition bg2_2Transition = createTranslateTransition(bg2_2, 15000);
        bg2_2parallelTransition = new ParallelTransition(bg1_2Transition, bg2_2Transition);
        bg2_2parallelTransition.setCycleCount(TranslateTransition.INDEFINITE);
        bg2_2parallelTransition.play();


        //for last background images
        TranslateTransition bg1_1Transition = createTranslateTransition(bg1_1, 20000);
        TranslateTransition bg2_1Transition = createTranslateTransition(bg2_1, 20000);
        bg1_1parallelTransition = new ParallelTransition(bg1_1Transition, bg2_1Transition);
        bg1_1parallelTransition.setCycleCount(TranslateTransition.INDEFINITE);
        bg1_1parallelTransition.play();

        //Enemy
        skeletonTransition = new TranslateTransition(Duration.millis(9000), skeleton);
        skeletonTransition.setFromX(0);
        skeletonTransition.setToX(BG_WIDTH * -1 -150);
        skeletonTransition.setInterpolator(Interpolator.LINEAR);
        skeletonTransition.setCycleCount(Animation.INDEFINITE);
        skeletonTransition.play();
        timer.start();
//        }
    }

    public TranslateTransition createTranslateTransition(ImageView imageView, int durationMillis) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(durationMillis), imageView);
        transition.setFromX(0);
        transition.setToX(BG_WIDTH * -1);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        return transition;
    }
}
