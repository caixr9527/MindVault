package com.mind.vault.controller;


import com.mind.vault.utils.AlertConfirmUtil;
import com.mind.vault.utils.ApiClient;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLView("/fxml/index.fxml")
@Component
public class IndexView extends AbstractFxmlView implements Initializable{

    // 左侧目录控制器
    public LeftDirectory leftDirectoryController;
    // 右侧预览控制器
    public RightPreview rightPreviewController;
    // 左边目录树
    public VBox leftDirectory;
    // 左边状态栏窗口复原
    public Button leftToggleBtn;
    // 左边状态栏窗口复原图标
    public FontIcon restoreLeftDirectoryIcon;
    // 中间主界面
    public SplitPane mainArea;
    // 左边目录树顶部
    public HBox leftDirectoryTop;

    // 右边预览区域
    public VBox rightPreview;
    // 右边预览区域顶部
    public HBox rightPreviewTop;
    // 右边状态栏窗口复原按钮
    public Button rightToggleBtn;
    // 右边状态栏窗口复原图标
    public FontIcon restoreRightPreviewIcon;


    @Autowired
    private ApiClient apiClient;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initController();
    }

    private void initController() {
        leftDirectoryController = LeftDirectory.builder()
                .leftDirectory(leftDirectory)
                .leftToggleBtn(leftToggleBtn)
                .restoreLeftDirectoryIcon(restoreLeftDirectoryIcon)
                .mainArea(mainArea)
                .leftDirectoryTop(leftDirectoryTop)
                .build();
        rightPreviewController = RightPreview.builder()
                .rightPreview(rightPreview)
                .rightToggleBtn(rightToggleBtn)
                .restoreRightPreviewIcon(restoreRightPreviewIcon)
                .mainArea(mainArea)
                .rightPreviewTop(rightPreviewTop)
                .build();
    }

    /**
     * 退出
     */
    public void exit(ActionEvent event) {
        if (AlertConfirmUtil.showConfirmAlert("退出 MindVault",null,"确定退出吗?",null)) {
            Platform.exit();
            System.exit(0);
        } else {
            event.consume();
        }
    }

    /**
     * 最小化左边窗口
     */
    public void minimizeLeftDirectory(ActionEvent event) {
        leftDirectoryController.minimizeLeftDirectory(event);
    }

    /**
     * 恢复左边窗口
     */
    public void restoreLeftDirectory(ActionEvent event) {
        leftDirectoryController.restoreLeftDirectory(event);
    }


    /**
     * 最小化右边窗口
     */
    public void minimizeRightPreview(ActionEvent event) {
        rightPreviewController.minimizeLeftDirectory(event);
    }

    /**
     * 恢复右边窗口
     */
    public void restoreRightPreview(ActionEvent event) {
        rightPreviewController.restoreLeftDirectory(event);
    }

    public void send(ActionEvent event) {
        apiClient.health().subscribe(r -> {
            System.out.println(r.getData());
        },e ->{
            System.out.println(e.getMessage());
        },() -> {
            System.out.println("complete");
        });
    }
}