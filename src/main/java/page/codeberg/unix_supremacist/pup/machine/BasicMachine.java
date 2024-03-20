package page.codeberg.unix_supremacist.pup.machine;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import page.codeberg.unix_supremacist.pup.Interfaces.ITSidedInventory;
import page.codeberg.unix_supremacist.pup.Tags;

public class BasicMachine extends BlockContainer {

    public static final Class Tile = BasicTile.class;
    private final Random random = new Random();
    private boolean isOn;
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BasicMachine() {
        super(Material.iron);
    }

    public void onBlockAdded(World w, int x, int y, int z) {
        super.onBlockAdded(w, x, y, z);
        if (!w.isRemote) {
            Block block = w.getBlock(x, y, z - 1);
            Block block1 = w.getBlock(x, y, z + 1);
            Block block2 = w.getBlock(x - 1, y, z);
            Block block3 = w.getBlock(x + 1, y, z);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) b0 = 3;
            if (block1.func_149730_j() && !block.func_149730_j()) b0 = 2;
            if (block2.func_149730_j() && !block3.func_149730_j()) b0 = 5;
            if (block3.func_149730_j() && !block2.func_149730_j()) b0 = 4;
            w.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int u, float u1, float u2, float u3) {
        if (!w.isRemote) {
            BasicTile tile = (BasicTile) w.getTileEntity(x, y, z);
            if (tile != null) p.openGui(Tags.MODID, 0, w, x, y, z);
        }
        return true;
    }

    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack i) {
        int l = MathHelper.floor_double((double) (p.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) w.setBlockMetadataWithNotify(x, y, z, 2, 2);
        if (l == 1) w.setBlockMetadataWithNotify(x, y, z, 5, 2);
        if (l == 2) w.setBlockMetadataWithNotify(x, y, z, 3, 2);
        if (l == 3) w.setBlockMetadataWithNotify(x, y, z, 4, 2);

        if (i.hasDisplayName()) ((BasicTile) w.getTileEntity(x, y, z)).setInventoryName(i.getDisplayName());
    }

    public void breakBlock(World w, int x, int y, int z, Block b, int u) {
        BasicTile tile = (BasicTile) w.getTileEntity(x, y, z);
        if (tile != null) {
            for (int i1 = 0; i1 < tile.getSizeInventory(); ++i1) {
                ItemStack itemstack = tile.getStackInSlot(i1);
                if (itemstack != null) {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int j1 = this.random.nextInt(21) + 10;
                        if (j1 > itemstack.stackSize) j1 = itemstack.stackSize;
                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(
                            w,
                            (float) x + f,
                            (float) y + f1,
                            (float) z + f2,
                            new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound()) entityitem.getEntityItem()
                            .setTagCompound(
                                (NBTTagCompound) itemstack.getTagCompound()
                                    .copy());

                        float f3 = 0.05F;
                        entityitem.motionX = (float) this.random.nextGaussian() * f3;
                        entityitem.motionY = (float) this.random.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) this.random.nextGaussian() * f3;
                        w.spawnEntityInWorld(entityitem);
                    }
                }
            }
            w.func_147453_f(x, y, z, b);
        }
        super.breakBlock(w, x, y, z, b, u);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new BasicTile();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }

    public class BasicTile extends TileEntity implements ITSidedInventory, IEnergyHandler {

        static final int[] slotsTop = new int[] { 0 };
        static final int[] slotsBottom = new int[] { 2 };
        public ItemStack[] inventory = new ItemStack[2];

        protected int processTime;
        protected String name;
        protected EnergyStorage storage = new EnergyStorage(32000);

        @Override
        public int[] getAccessibleSlotsFromSide(int side) {
            return side == 0 ? slotsBottom : (side == 1 ? slotsTop : null);
        }

        @Override
        public int getSizeInventory() {
            return this.inventory.length;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            return this.inventory[slot];
        }

        @Override
        public ItemStack decrStackSize(int slot, int count) {
            ItemStack i = null;
            if (this.inventory[slot] != null) if (this.inventory[slot].stackSize <= count) {
                i = this.inventory[slot];
                this.inventory[slot] = null;
            } else {
                i = this.inventory[slot].splitStack(count);
                if (this.inventory[slot].stackSize == 0) this.inventory[slot] = null;
            }
            return i;
        }

        // Unneeded for machines, could be useful on destruction
        @Override
        public ItemStack getStackInSlotOnClosing(int slot) {
            ItemStack i = null;
            if (this.inventory[slot] != null) {
                i = this.inventory[slot];
                this.inventory[slot] = null;
            }
            return i;
        }

        @Override
        public void setInventorySlotContents(int slot, ItemStack item) {
            this.inventory[slot] = item;
            if (item != null && item.stackSize > this.getInventoryStackLimit())
                item.stackSize = this.getInventoryStackLimit();
        }

        @Override
        public String getInventoryName() {
            return this.hasCustomInventoryName() ? this.name : "container";
        }

        public void setInventoryName(String name) {
            this.name = name;
        }

        @Override
        public boolean hasCustomInventoryName() {
            return this.name != null && this.name.length() > 0;
        }

        @Override
        public int getInventoryStackLimit() {
            return 64;
        }

        @Override
        public boolean isUseableByPlayer(EntityPlayer player) {
            return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D)
                <= 64.0D;
        }

        @Override
        public void openInventory() {}

        @Override
        public void closeInventory() {}

        @Override
        public boolean isItemValidForSlot(int slot, ItemStack item) {
            return true;
        }

        public void readFromNBT(NBTTagCompound tag) {
            super.readFromNBT(tag);
            NBTTagList list = tag.getTagList("Items", 10);
            this.inventory = new ItemStack[this.getSizeInventory()];

            for (int i = 0; i < list.tagCount(); ++i) {
                NBTTagCompound tag2 = list.getCompoundTagAt(i);
                byte b = tag2.getByte("Slot");
                if (/* b > 0 && */b < this.inventory.length) this.inventory[b] = ItemStack.loadItemStackFromNBT(tag2);
            }

            if (tag.hasKey("CustomName", 8)) this.name = tag.getString("CustomName");
            storage.readFromNBT(tag);
        }

        @Override
        public void writeToNBT(NBTTagCompound tag) {
            super.writeToNBT(tag);
            NBTTagList list = new NBTTagList();

            for (byte i = 0; i < this.inventory.length; ++i) {
                if (this.inventory[i] != null) {
                    NBTTagCompound tag2 = new NBTTagCompound();
                    tag2.setByte("Slot", i);
                    this.inventory[i].writeToNBT(tag2);
                    list.appendTag(tag2);
                }
            }

            tag.setTag("Items", list);
            if (this.hasCustomInventoryName()) tag.setString("CustomName", this.name);
            storage.writeToNBT(tag);
        }

        private boolean canProcess() {
            boolean canProcess = false;
            if (this.inventory[0] != null) {
                canProcess = true;
                ItemStack itemstack = FurnaceRecipes.smelting()
                    .getSmeltingResult(this.inventory[0]);
                if (itemstack == null) canProcess = false;
                if (this.inventory[1] != null) {
                    if (!this.inventory[1].isItemEqual(itemstack)) canProcess = false;
                    int result = inventory[1].stackSize + itemstack.stackSize;
                    canProcess = result <= getInventoryStackLimit() && result <= this.inventory[1].getMaxStackSize();
                }
            }
            return canProcess;
        }

        public void processItem() {
            if (this.canProcess()) {
                ItemStack i = FurnaceRecipes.smelting()
                    .getSmeltingResult(this.inventory[0]);
                if (this.inventory[1] == null) this.inventory[1] = i.copy();
                else if (this.inventory[1].getItem() == i.getItem()) this.inventory[1].stackSize += i.stackSize;

                this.inventory[0].stackSize--;

                if (this.inventory[0].stackSize < 0) this.inventory[0] = null;
            }
        }

        @Override
        public void updateEntity() {
            boolean dirty = false;
            if (!this.worldObj.isRemote) {
                if (this.storage.getEnergyStored() != 0 && this.inventory[0] != null) {
                    if (this.storage.getEnergyStored() > 0 && this.canProcess()) {
                        this.processTime++;
                        this.storage.modifyEnergyStored(-100);

                        if (this.processTime == 200) {
                            this.processTime = 0;
                            this.processItem();
                            dirty = true;
                        }
                    } else this.processTime = 0;
                }
            }

            if (dirty) this.markDirty();
        }

        public BasicContainer getContainer(InventoryPlayer playerInventory) {
            return new BasicContainer(playerInventory, this);
        }

        @SideOnly(Side.CLIENT)
        public Gui getGui(InventoryPlayer playerInventory) {
            return new Gui(playerInventory, this);
        }

        /* IEnergyHandler */
        @Override
        public boolean canConnectEnergy(ForgeDirection from) {
            return true;
        }

        @Override
        public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
            return storage.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
            return storage.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int getEnergyStored(ForgeDirection from) {
            return storage.getEnergyStored();
        }

        @Override
        public int getMaxEnergyStored(ForgeDirection from) {
            return storage.getMaxEnergyStored();
        }
    }

    @SideOnly(Side.CLIENT)
    public class Gui extends GuiContainer {

        private BasicTile tile;
        private ResourceLocation guiTextures = new ResourceLocation("textures/gui/container/container.png");

        public Gui(InventoryPlayer player, BasicTile tile) {
            super(new BasicContainer(player, tile));
            this.tile = tile;
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
            this.mc.getTextureManager()
                .bindTexture(guiTextures);
        }
    }

    public class BasicContainer extends Container {

        private final BasicTile tile;

        public BasicContainer(InventoryPlayer playerInventory, BasicTile tile) {
            this.tile = tile;
            this.addSlotToContainer(new Slot(tile, 0, 56, 17));
            this.addSlotToContainer(new SlotFurnace(playerInventory.player, tile, 1, 116, 35));
            int i;

            for (i = 0; i < 3; ++i) for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

            for (i = 0; i < 9; ++i) this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        @Override
        public boolean canInteractWith(EntityPlayer player) {
            return this.tile.isUseableByPlayer(player);
        }

        @Override
        public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
            ItemStack stack = null;
            Slot slot = (Slot) this.inventorySlots.get(slotNum);
            if (slot != null && slot.getHasStack()) {
                ItemStack slotStack = slot.getStack();
                stack = slotStack.copy();
                if (slotNum == 2) {
                    if (!this.mergeItemStack(slotStack, 3, 39, true)) return null;
                    slot.onSlotChange(slotStack, stack);
                } else if (slotNum != 1 && slotNum != 0) {
                    if (FurnaceRecipes.smelting()
                        .getSmeltingResult(slotStack) != null)
                        if (!this.mergeItemStack(slotStack, 0, 1, false)) return null;
                    else if (TileEntityFurnace.isItemFuel(slotStack))
                        if (!this.mergeItemStack(slotStack, 1, 2, false)) return null;
                    else if (slotNum < 30) if (!this.mergeItemStack(slotStack, 30, 39, false)) return null;
                    else if (slotNum < 39 && !this.mergeItemStack(slotStack, 3, 30, false)) return null;
                } else if (!this.mergeItemStack(slotStack, 3, 39, false)) return null;

                if (slotStack.stackSize == 0) slot.putStack((ItemStack) null);
                else slot.onSlotChanged();

                if (slotStack.stackSize == stack.stackSize) return null;

                slot.onPickupFromSlot(player, slotStack);
            }
            return stack;
        }
    }
}
