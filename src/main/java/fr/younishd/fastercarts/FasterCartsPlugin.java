package fr.younishd.fastercarts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FasterCartsPlugin extends JavaPlugin implements Listener {

    private double speed;

    private static final double DEFAULT_SPEED = 0.4d;

    @Override
    public void onEnable() {
        this.getLogger().info("Hello.");
        this.getServer().getPluginManager().registerEvents(this, this);

        this.speed = DEFAULT_SPEED;
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Bye.");
    }

    @EventHandler
    public void onVehicleCreate(VehicleCreateEvent e) {
        if (e.getVehicle() instanceof Minecart) {
            Minecart cart = (Minecart) e.getVehicle();
            cart.setMaxSpeed(this.speed);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cartspeed")) {
            if (args.length > 1) {
                sender.sendMessage("Wrong number of arguments.");
                return false;
            }

            if (args.length == 1) {
                double speed;
                try {
                    speed = Double.parseDouble(args[0]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Invalid number format.");
                    return false;
                }
                this.speed = speed;
            }

            sender.sendMessage("Cart speed: " + this.speed);
            return true;
        }

        return false;
    }

}
