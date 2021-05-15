package mod.pianomanu.ddd.world;

import mod.pianomanu.ddd.DDDMain;
import mod.pianomanu.ddd.config.DDDConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

/**
 * Will add description later...
 *
 * @author PianoManu
 * @version 1.0 05/15/21
 */
public class DDDTeleporter implements ITeleporter {
    private static BlockPos overworldTeleporterPos;

    public DDDTeleporter(BlockPos overworldTeleporterPos) {
        DDDTeleporter.overworldTeleporterPos = overworldTeleporterPos;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity e = repositionEntity.apply(false);
        if (e instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) e;
            BlockPos teleporterPos;
            if (destWorld.dimension().equals(DDDMain.DEEP_DARK_DIMENSION))
                teleporterPos = DDDConfig.SPAWN_POS;
            else
                teleporterPos = overworldTeleporterPos;
            player.teleportTo(teleporterPos.getX(), teleporterPos.getY(), teleporterPos.getZ());
        }
        return e;
    }

    public void setOverworldTeleporterPos(BlockPos newOverworldTeleporterPos) {
        overworldTeleporterPos = newOverworldTeleporterPos;
    }

    public static BlockPos getOverworldTeleporterPos() {
        return overworldTeleporterPos;
    }
}
//========SOLI DEO GLORIA========//