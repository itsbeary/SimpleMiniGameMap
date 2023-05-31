package net.kealands.creativetest.utils;

import net.kealands.creativetest.CreativeTest;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public record PastedBlock(double x, double y, double z, double id, BlockData data) {

    public static class BlockQueue {

        private final Deque<PastedBlock> queue = new ConcurrentLinkedDeque<>();
        private static final Map<World, BlockQueue> queueMap = new ConcurrentHashMap<>();

        public void add(PastedBlock block) {
            queue.add(block);
        }

        public BlockQueue(final World world) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(CreativeTest.getPlugin(), () -> {
                PastedBlock block;
                while ((block = queue.poll()) != null) {
                    world.getBlockAt((int) block.x, (int) block.y, (int) block.z).setBlockData(block.data, false);
                }
            }, 1, 1);
        }

        public static BlockQueue getQueue(World w) {
            if (!queueMap.containsKey(w)) {
                BlockQueue blockQueue = new BlockQueue(w);
                queueMap.put(w, blockQueue);

                return blockQueue;
            }
            return queueMap.get(w);
        }
    }
}