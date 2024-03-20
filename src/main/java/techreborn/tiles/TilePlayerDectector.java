package techreborn.tiles;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import techreborn.powerSystem.TilePowerAcceptor;

public class TilePlayerDectector extends TilePowerAcceptor {

    boolean redstone = false;
    public String owenerUdid = "";

    public TilePlayerDectector() {
        super(1);
    }

    @Override
    public double getMaxPower() {
        return 10000;
    }

    @Override
    public boolean canAcceptEnergy(ForgeDirection direction) {
        return true;
    }

    @Override
    public boolean canProvideEnergy(ForgeDirection direction) {
        return false;
    }

    @Override
    public double getMaxOutput() {
        return 0;
    }

    @Override
    public double getMaxInput() {
        return 32;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote && worldObj.getWorldTime() % 20 == 0) {
            boolean lastRedstone = redstone;
            redstone = false;
            if (canUseEnergy(50)) {
                Iterator tIterator = super.worldObj.playerEntities.iterator();
                while (tIterator.hasNext()) {
                    EntityPlayer player = (EntityPlayer) tIterator.next();
                    if (player.getDistanceSq(
                        (double) super.xCoord + 0.5D,
                        (double) super.yCoord + 0.5D,
                        (double) super.zCoord + 0.5D) <= 256.0D) {
                        if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 0) {// ALL
                            redstone = true;
                        } else if (blockMetadata == 1) {// Others
                            if (!owenerUdid.isEmpty() && !owenerUdid.equals(
                                player.getUniqueID()
                                    .toString())) {
                                redstone = true;
                            }
                        } else {// You
                            if (!owenerUdid.isEmpty() && owenerUdid.equals(
                                player.getUniqueID()
                                    .toString())) {
                                redstone = true;
                            }
                        }
                    }
                }
                useEnergy(50);
            }
            if (lastRedstone != redstone) {
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                worldObj
                    .notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
            }
        }
    }

    public boolean isProvidingPower() {
        return redstone;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        owenerUdid = tag.getString("ownerID");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("ownerID", owenerUdid);
    }
}
