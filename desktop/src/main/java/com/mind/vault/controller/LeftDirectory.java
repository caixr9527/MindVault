package com.mind.vault.controller;

import com.mind.vault.utils.AlertConfirmUtil;
import javafx.application.Platform;
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
 * @date 2025/10/16 17:44
 */
@Builder
@AllArgsConstructor
public class LeftDirectory {

    // 左边目录树
    private VBox leftDirectory;
    // 左边状态栏窗口复原
    private Button leftToggleBtn;
    // 左边状态栏窗口复原图标
    private FontIcon restoreLeftDirectoryIcon;
    // 中间主界面
    private SplitPane mainArea;
    // 左边目录树顶部
    private HBox leftDirectoryTop;

    private LeftDirectory() {

    }



    /**
     * 最小化窗口
     */
    public void minimizeLeftDirectory(ActionEvent event) {
        leftDirectory.setVisible(false);
        leftDirectory.setManaged(false);
        restoreLeftDirectoryIcon.setVisible(true);
        mainArea.setDividerPosition(0,0);
    }

    /**
     * 恢复窗口
     */
    public void restoreLeftDirectory(ActionEvent event) {
        leftDirectory.setVisible(true);
        leftDirectory.setManaged(true);
        restoreLeftDirectoryIcon.setVisible(false);
        mainArea.setDividerPosition(0,0.2);
    }

}
