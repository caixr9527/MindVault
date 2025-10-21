package com.mind.vault.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * @author caixr
 * @date 2025/10/16 18:03
 */
@Builder
@AllArgsConstructor
public class RightPreview {

    private VBox rightPreview;
    private HBox rightPreviewTop;
    private Button rightToggleBtn;
    private FontIcon restoreRightPreviewIcon;
    // 中间主界面
    private SplitPane mainArea;

    private RightPreview() {
    }

    /**
     * 最小化窗口
     */
    public void minimizeLeftDirectory(ActionEvent event) {
        rightPreview.setVisible(false);
        rightPreview.setManaged(false);
        restoreRightPreviewIcon.setVisible(true);
        mainArea.setDividerPosition(1,1);
    }

    /**
     * 恢复窗口
     */
    public void restoreLeftDirectory(ActionEvent event) {
        rightPreview.setVisible(true);
        rightPreview.setManaged(true);
        restoreRightPreviewIcon.setVisible(false);
        mainArea.setDividerPosition(1,0.7);
    }
}
