package techreborn.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.powerSystem.TilePowerAcceptor;

public class TileHeatGenerator extends TilePowerAcceptor {

    public static final int euTick = ConfigTechReborn.heatGeneratorOutput;

    public TileHeatGenerator() {
        super(1);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isRemote) {
            if (worldObj.getBlock(xCoord + 1, yCoord, zCoord) == Blocks.lava) {
                addEnergy(euTick);
            } else if (worldObj.getBlock(xCoord, yCoord, zCoord + 1) == Blocks.lava) {
                addEnergy(euTick);
            } else if (worldObj.getBlock(xCoord, yCoord, zCoord - 1) == Blocks.lava) {
                addEnergy(euTick);
            } else if (worldObj.getBlock(xCoord - 1, yCoord, zCoord) == Blocks.lava) {
                addEnergy(euTick);
            } else if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.lava) {
                addEnergy(euTick);
            }

        }
    }

    public boolean isComplete() {
        return false;
    }

    @Override
    public double getMaxPower() {
        return 10000;
    }

    @Override
    public boolean canAcceptEnergy(ForgeDirection direction) {
        return false;
    }

    @Override
    public boolean canProvideEnergy(ForgeDirection direction) {
        return true;
    }

    @Override
    public double getMaxOutput() {
        return 64;
    }

    @Override
    public double getMaxInput() {
        return 0;
    }

    // @Override
    // public void addWailaInfo(List<String> info)
    // {
    // super.addWailaInfo(info);
    // info.add("Power Generarating " + euTick +" EU/t");
    //
    // }

}
