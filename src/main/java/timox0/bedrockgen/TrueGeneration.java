package timox0.bedrockgen;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrueGeneration extends ChunkGenerator {

    @Override
    public void generateBedrock(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        gens.get(random.nextInt(gens.size())).generate(worldInfo, random, chunkX, chunkZ, chunkData);
    }

    @Override
    public boolean shouldGenerateNoise() {
        return true;
    }

    @Override
    public boolean shouldGenerateSurface() {
        return true;
    }

    public TrueGeneration() {
        gens = new ArrayList<>();
        gens.add((worldInfo, random, chunkX, chunkZ, chunkData) -> {});
        CustomWorldGenerator bedrok = new CustomWorldGenerator();
        //gens.add(bedrok::generateNoise);
        BedrokInverse bedrokInverse = new BedrokInverse();
        //gens.add(bedrokInverse::generateNoise);
        List<Material> blocks = Arrays.stream(Material.values()).filter(material -> material.isSolid() && !material.isInteractable()).toList();
        gens.add((worldInfo, random, chunkX, chunkZ, chunkData) -> {
            int shift = random.nextInt(1,500)%blocks.size();
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = worldInfo.getMinHeight(); y < worldInfo.getMaxHeight(); y++) {
                        if (chunkData.getType(x,y,z)==Material.AIR || !blocks.contains(chunkData.getType(x,y,z))) continue;
                        chunkData.setBlock(x,y,z,blocks.get((blocks.indexOf(chunkData.getType(x,y,z))+shift)%blocks.size()));
                    }
                }
            }
        });
    }
    public static ArrayList<Generator> gens;

    @FunctionalInterface
    public interface Generator {
        void generate(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData);
    }
}
