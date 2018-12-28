package com.cn.sleep.study.test;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.Museum;
import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.enums.Type;

import java.util.ArrayList;
import java.util.List;

public class MuseumTest {

    public Museum base() {
        Museum museum = new Museum();
        museum.setHref("123");
        museum.setIcon("fa fa-circle");
        museum.setName("菜单");
        museum.setPullRightIcon("fa fa-angle-left");
        return museum;
    }

    public Museum createItem() {
        Museum museum = base();
        museum.setType(Type.TYPE_ITEM);
        return museum;
    }

    public Museum createRoot() {
        Museum museum = base();
        museum.setType(Type.TYPE_PARENT);
        return museum;
    }

    public Museum createLabel() {
        Museum museum = base();
        museum.setType(Type.TYPE_LABEL);
        return museum;
    }

    public void createSubSub(Museum museum, int start) {
        for (int i = 0; i < 10; i++) {
            Museum sub = createItem();
            museum.getSubMuseum().add(sub);
            if (start == 0) {
                continue;
            }
            createSubSub(sub, --start);
        }
    }

    public void addItem(Museum root, int number) {
        for (int i = 0; i < number; i++) {
            root.getSubMuseum().add(createItem());
        }
    }

    public void addRootToLab(Museum label, int number) {
        for (int i = 0; i < number; i++) {
            label.getSubMuseum().add(createRoot());
        }
    }

    public List<Museum> create() {
        List<Museum> museumList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Museum root = createRoot();
            addItem(root, 10);
            createSubSub(root,3);
            museumList.add(root);
        }
        for (int i = 0; i < 10; i++) {
            Museum label = createLabel();
            addRootToLab(label, 10);
            label.getSubMuseum().forEach(museum -> {
                addItem(museum, 10);
            });
            museumList.add(label);
        }
        return museumList;
    }


}
