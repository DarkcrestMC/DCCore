package com.Jacksonnn.DCCore.AutoAnnouncer;

import com.Jacksonnn.DCCore.DCCore;

public class AnnouncerThread extends Thread {
    private final DCCore plugin;

    private int lastAnnouncement = 0;

    public AnnouncerThread(DCCore plugin) {
        this.plugin = plugin;
    }

    public void run() {
        if (AnnouncementManager.isEnabled()) {
            if (this.lastAnnouncement >= AnnouncementManager.getAnnouncements().size())
                this.lastAnnouncement = 0;
            if (this.lastAnnouncement < AnnouncementManager.getAnnouncements().size())
                AnnouncementManager.announce(this.lastAnnouncement++);
        }
    }
}
