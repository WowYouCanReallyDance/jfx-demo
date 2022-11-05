package com.demos.jfxdemo;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final AnchorPane rootPane = new AnchorPane();
        rootPane.setBackground(
                new Background(
                        new BackgroundFill(
                                Paint.valueOf("#ffc20e36"),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );
        final VBox vbCard = new VBox();
        vbCard.setBackground(
                new Background(
                        new BackgroundFill(
                                Paint.valueOf("#33a3dca6"),
                                new CornerRadii(20),
                                Insets.EMPTY
                        )
                )
        );
        vbCard.prefWidthProperty().bind(rootPane.widthProperty().divide(2));
        vbCard.prefHeightProperty().bind(rootPane.heightProperty().divide(2));
        vbCard.translateXProperty().bind(rootPane.widthProperty().subtract(vbCard.widthProperty()).divide(2));
        vbCard.translateYProperty().bind(rootPane.heightProperty().subtract(vbCard.heightProperty()).divide(2));
        final TextField umInput = new TextField();
        final DoubleBinding vbCardWidthBinding = vbCard.widthProperty().multiply(0.8);
        final DoubleBinding vbCardHeightBinding = vbCard.heightProperty().multiply(1 / 9.0);
        umInput.maxWidthProperty().bind(vbCardWidthBinding);
        umInput.prefHeightProperty().bind(vbCardHeightBinding);
        umInput.setMinSize(0, 0);
        umInput.setAlignment(Pos.CENTER);
        final PasswordField pwInput = new PasswordField();
        pwInput.maxWidthProperty().bind(vbCardWidthBinding);
        pwInput.prefHeightProperty().bind(vbCardHeightBinding);
        pwInput.setMinSize(0, 0);
        pwInput.setAlignment(Pos.CENTER);
        final HBox hboxBar = new HBox();
        hboxBar.maxWidthProperty().bind(vbCardWidthBinding);
        hboxBar.prefHeightProperty().bind(vbCardHeightBinding);
        final DoubleBinding hboxBarWidthBinding = hboxBar.widthProperty().divide(2);
        final HBox innerLeftBox = new HBox();
        innerLeftBox.prefWidthProperty().bind(hboxBarWidthBinding);
        final HBox innerRightBox = new HBox();
        innerRightBox.prefWidthProperty().bind(hboxBarWidthBinding);
        final Button loginBtn = new Button("Login");
        loginBtn.prefWidthProperty().bind(innerLeftBox.widthProperty().multiply(0.9));
        loginBtn.prefHeightProperty().bind(innerLeftBox.heightProperty());
        loginBtn.setMinSize(0, 0);
        loginBtn.setBackground(
                new Background(
                        new BackgroundFill(
                                Paint.valueOf("#1d953f"),
                                new CornerRadii(3),
                                Insets.EMPTY
                        )
                )
        );
        loginBtn.setOnAction(event -> {
            final Alert alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    String.format("What you have input:\nUserName: %s\nPassWord: %s", umInput.getText(), pwInput.getText()),
                    ButtonType.OK
            );
            alert.show();
            URL audioUrl = MainApplication.class.getResource("/audio/qq_beep.mp3");
            if (audioUrl != null) {
                final AudioClip tip = new AudioClip(audioUrl.toExternalForm());
                tip.play();
            }
        });
        final Button clearBtn = new Button("Clear");
        clearBtn.prefWidthProperty().bind(innerRightBox.widthProperty().multiply(0.9));
        clearBtn.prefHeightProperty().bind(innerRightBox.heightProperty());
        clearBtn.setMinSize(0, 0);
        clearBtn.setBackground(
                new Background(
                        new BackgroundFill(
                                Paint.valueOf("#f47920"),
                                new CornerRadii(3),
                                Insets.EMPTY
                        )
                )
        );
        clearBtn.setOnAction(event -> {
            umInput.setText(null);
            pwInput.setText(null);
        });
        innerLeftBox.getChildren().add(loginBtn);
        innerLeftBox.setAlignment(Pos.CENTER_LEFT);
        innerRightBox.getChildren().add(clearBtn);
        innerRightBox.setAlignment(Pos.CENTER_RIGHT);
        hboxBar.getChildren().addAll(
                innerLeftBox,
                innerRightBox
        );
        hboxBar.setAlignment(Pos.CENTER);
        vbCard.getChildren().addAll(
                umInput,
                pwInput,
                hboxBar
        );
        vbCard.setAlignment(Pos.CENTER);
        vbCard.spacingProperty().bind(vbCardHeightBinding);
        rootPane.getChildren().add(vbCard);
        final Scene scene = new Scene(rootPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}