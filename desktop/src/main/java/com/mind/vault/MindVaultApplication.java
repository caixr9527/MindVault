package com.mind.vault;

import com.mind.vault.controller.IndexView;
import com.mind.vault.utils.AlertConfirmUtil;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

@Slf4j
@SpringBootApplication
public class MindVaultApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        // 解决Netty在macOS上的DNS解析本地库加载错误
        System.setProperty("io.netty.resolver.dns.native.mac.osx.disable", "true");
        launch(MindVaultApplication.class, IndexView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("MindVault");

        // 设置应用程序图标
        try {
            // 为确保在任务栏正确显示，添加多种尺寸的图标
            // 按照从大到小的顺序添加，系统会选择最合适的尺寸
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mindVault.png")), 128, 128, true, true));
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mindVault.png")), 64, 64, true, true));
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mindVault.png")), 48, 48, true, true));
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mindVault.png")), 32, 32, true, true));
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mindVault.png")), 16, 16, true, true));
        } catch (Exception e) {
            log.error("加载图标失败:", e);
        }
        Scene scene = stage.getScene();
        if (scene != null) {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/styles.css")).toExternalForm());
        }
    }


    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setOnCloseRequest(event -> {
            if (AlertConfirmUtil.showConfirmAlert("退出 MindVault",null,"确定退出吗?",null)) {
                Platform.exit();
                System.exit(0);
            } else {
                event.consume();
            }
        });
    }
}