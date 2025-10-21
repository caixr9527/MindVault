package com.mind.vault.utils;

import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.Objects;
import java.util.Optional;

/**
 * @author caixr
 * @date 2025/10/16 14:53
 */
public class AlertConfirmUtil {

    /**
     * 显示确认对话框
     *
     * @param title 对话框标题
     * @param headerText 对话框头部文本
     * @param contentText 对话框内容文本
     * @param Graphic 对话框图标节点
     * @return 用户点击"是"按钮返回true，点击"否"按钮返回false
     */
    public static boolean showConfirmAlert(String title, String headerText, String contentText, Node Graphic) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.setGraphic(Graphic);

        // 添加样式表到对话框
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(AlertConfirmUtil.class.getResource("/resources/styles.css")).toExternalForm());

        ButtonType yesButton = new ButtonType("是 (Y)", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("否 (N)", ButtonBar.ButtonData.NO);
        alert.getDialogPane().getButtonTypes().setAll(yesButton, noButton);

        // 设置现代化简约按钮样式
        Button yesBtn = (Button) dialogPane.lookupButton(yesButton);
        Button noBtn = (Button) dialogPane.lookupButton(noButton);

        yesBtn.getStyleClass().add("exit-dialog-button");
        noBtn.getStyleClass().add("exit-dialog-button");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }

}
