package DevNyanha.bitcoinAPI;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.text.DecimalFormat;

public class PlaceHolder extends PlaceholderExpansion {
    @Override
    public @NotNull String getAuthor() {
        return "DevNyanha";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "bitcoin";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "error";
        } else {
            if (params.startsWith("get_")) {
                String country = params.substring("get_".length());
                for (int i = 0; i < BitcoinAPI.getCountryList().length; i++) {
                    if (BitcoinAPI.getCountryList()[i].equals(country)) {
                        return BitcoinAPI.getCoinValueList()[i];
                    }
                }
            } else if (params.startsWith("formatted_")) {
                String country = params.substring("formatted_".length());
                for (int i = 0; i < BitcoinAPI.getCountryList().length; i++) {
                    if (BitcoinAPI.getCountryList()[i].equals(country)) {
                        String numberStr = BitcoinAPI.getCoinValueList()[i];
                        long number = Long.parseLong(numberStr);
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        return formatter.format(number);
                    }
                }
            }
            return "error";
        }
    }
}
