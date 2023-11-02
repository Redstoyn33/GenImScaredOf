package timox0.bedrockgen;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.noise.PerlinNoiseGenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;

public final class BedrockGen extends JavaPlugin {
    public static File file;
    public static BedrockGen plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new TrueGeneration();
    }
}
