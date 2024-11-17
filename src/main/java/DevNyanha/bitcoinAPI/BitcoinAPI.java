package DevNyanha.bitcoinAPI;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;

public final class BitcoinAPI extends JavaPlugin {
    private int apiInterval;
    private static String[] countryList;
    private static String[] coinValueList = new String[4];

    public static String[] getCountryList() {
        return countryList;
    }

    public static String[] getCoinValueList() {
        return coinValueList;
    }

    private void configReload() {
        apiInterval = getConfig().getInt("api-interval", 60);
        countryList = getConfig().getStringList("country-list").toArray(new String[0]);
    }

    private void setCoinValue() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < countryList.length; i++) {
                    coinValueList[i] = RequestAPI.getCoin(countryList[i]);
                    getLogger().info(coinValueList[i]);
                }
            }
        }.runTaskTimer(this, 0L, apiInterval * 20L);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        configReload();
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //
            (new PlaceHolder()).register();
        }
        setCoinValue();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
