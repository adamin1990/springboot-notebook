package com.adamin.demo.listener.event;

import com.adamin.demo.entity.Box;
import org.springframework.context.ApplicationEvent;

/**
 * @Classname BoxEvent
 * @Description 自定义事件
 * @Date 2022/4/11 11:39
 * @Created by Adam(https://www.lixiaopeng.top)
 */
public class BoxEvent extends ApplicationEvent {
    private Box box;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public BoxEvent(Object source,Box box) {
        super(source);
        this.box=box;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}
