package com.guagua.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author guagua
 * @date 2022/11/13 22:08
 * @describe
 */
public class UserDelayed implements Delayed {

    private String username;

    private long delayTime;

    public UserDelayed(String username, int delayTime) {
        this.username = username;
        this.delayTime = (delayTime * 1000L) + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.delayTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.delayTime - ((UserDelayed) o).delayTime);
    }

    @Override
    public String toString() {
        return "UserDelayed{" +
                "username='" + username + '\'' +
                ", delayTime=" + delayTime +
                '}';
    }
}
