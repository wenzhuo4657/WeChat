package netty_javafx.project.wechat.view.InitSupport;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//初始化页面顶级父类，其中定义了初始化页面的抽象方法和基本的窗口移动事件
public abstract class UIObject extends Stage {

    protected Parent root;
    private double xOffset;
    private double yOffset;


    /**
     * 获取根据id和class对象获取指定节点对象
     * @param id
     * @param clazz
     * @return
     * @param <T>
     */
    public  <T> T $(String id, Class<T> clazz) {
        return (T) root.lookup("#" + id);
    }

    public void clearViewListSelectedAll(ListView<Pane>... listViews) {
        for (ListView<Pane> listView : listViews) {
            listView.getSelectionModel().clearSelection();
        }
    }

/**
* @Author wenzhuo4657
* @Description  窗口移动事件 ,基本事件
* @Date 15:53 2024-04-26
* @Param []
* @return void
**/
    public void move() {
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });
        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }

    // 初始化页面
    public abstract void initView();

    // 初始化事件定义
    public abstract void initEventDefine();

}