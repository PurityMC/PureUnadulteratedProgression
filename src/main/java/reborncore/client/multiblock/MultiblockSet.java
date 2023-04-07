/**
 * This class was created by <Vazkii>. It's distributed as part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p/>
 * Botania is Open Source and distributed under the Botania License: http://botaniamod.net/license.php
 */
package reborncore.client.multiblock;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * A set of Multiblock objects for various rotations.
 */
public class MultiblockSet {

    private final Multiblock[] mbs;

    public MultiblockSet(Multiblock[] mbs) {
        this.mbs = mbs;
    }

    public MultiblockSet(Multiblock mb) {
        this(mb.createRotations());
    }

    public Multiblock getForEntity(Entity e) {
        return getForRotation(e.rotationYaw);
    }

    public Multiblock getForRotation(double rotation) {
        int facing = MathHelper.floor_double(rotation * 4.0 / 360.0 + 0.5) & 3;
        return getForIndex(facing);
    }

    public Multiblock getForIndex(int index) {
        return mbs[index];
    }
}
