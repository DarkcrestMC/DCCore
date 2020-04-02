package com.Jacksonnn.DCCore.StaffCounts;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StaffCountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        GeneralMethods.updateStaffCount(null);
        return true;
    }
}
