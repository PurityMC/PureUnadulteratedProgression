package techreborn.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

import techreborn.Core;
import techreborn.client.TechRebornCreativeTab;
import techreborn.init.ModItems;

public class ItemCells extends ItemTR implements IFluidContainerItem {

    public static ItemStack getCellByName(String name, int count) {
        return getCellByName(name, count, true);
    }

    public static ItemStack getCellByName(String name, int count, boolean lookForIC2) {
        Fluid fluid = FluidRegistry.getFluid("fluid" + name.toLowerCase());
        // if (lookForIC2 && IC2Items.getItem("cell") != null) {
        //     if (fluid != null) {
        //         ItemStack stack = IC2Items.getItem("cell")
        //             .copy();
        //         if (stack != null && stack.getItem() instanceof IFluidContainerItem) {
        //             IFluidContainerItem containerItem = (IFluidContainerItem) stack.getItem();
        //             containerItem.fill(stack, new FluidStack(fluid.getID(), 2147483647), true);
        //             stack.stackSize = count;
        //             return stack;
        //         }
        //     } else {
        //         Core.logHelper.debug("Could not find " + "fluid" + name + " in the fluid registry!");
        //     }
        // }
        int index = -1;
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(name)) {
                index = i;
                break;
            }
        }
        return new ItemStack(ModItems.cells, count, index);
    }

    public static ItemStack getCellByName(String name) {
        return getCellByName(name, 1);
    }

    public static final String[] types = new String[] { "Berylium", "biomass", "calciumCarbonate", "calcium", "carbon",
        "chlorine", "deuterium", "diesel", "ethanol", "glyceryl", "helium3", "helium", "heliumPlasma", "hydrogen",
        "ice", "lithium", "mercury", "methane", "nitrocarbon", "nitroCoalfuel", "nitroDiesel", "nitrogen",
        "nitrogenDioxide", "oil", "potassium", "seedOil", "silicon", "sodium", "sodiumPersulfate", "sodiumSulfide",
        "sulfur", "sulfuricAcid", "tritium", "wolframium", "empty" };

    private IIcon[] textures;

    public ItemCells() {
        setUnlocalizedName("techreborn.cell");
        setHasSubtypes(true);
        setCreativeTab(TechRebornCreativeTab.instance);
    }

    @Override
    // Registers Textures For All Dusts
    public void registerIcons(IIconRegister iconRegister) {
        textures = new IIcon[types.length];

        for (int i = 0; i < types.length; ++i) {
            textures[i] = iconRegister.registerIcon("techreborn:" + "cells/" + types[i] + "Cell");
        }
    }

    @Override
    // Adds Texture what match's meta data
    public IIcon getIconFromDamage(int meta) {
        if (meta < 0 || meta >= textures.length) {
            meta = 0;
        }

        return textures[meta];
    }

    @Override
    // gets Unlocalized Name depending on meta data
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage();
        if (meta < 0 || meta >= types.length) {
            meta = 0;
        }

        return super.getUnlocalizedName() + "." + types[meta];
    }

    // Adds Dusts SubItems To Creative Tab
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int meta = 0; meta < types.length; ++meta) {
            ItemStack stack = new ItemStack(item, 1, meta);
            if (FluidRegistry.getFluid("fluid" + types[meta].toLowerCase()) != null) {
                this.fill(
                    stack,
                    new FluidStack(FluidRegistry.getFluid("fluid" + types[meta].toLowerCase()), getCapacity(stack)),
                    true);
            }
            list.add(stack);
        }
    }

    @Override
    public FluidStack getFluid(ItemStack container) {
        return FluidStack.loadFluidStackFromNBT(container.getTagCompound());
    }

    @Override
    public int getCapacity(ItemStack container) {
        return 1000;
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill) {
        if (container.stackSize != 1) {
            return 0;
        }
        if (resource == null || resource.amount != getCapacity(container)) {
            return 0;
        }
        if (FluidRegistry.getFluid("fluid" + types[container.getItemDamage()].toLowerCase()) == null) {
            return 0;
        }
        if (doFill) {
            NBTTagCompound tag = container.getTagCompound();
            if (tag == null) {
                tag = new NBTTagCompound();
            }
            resource.writeToNBT(tag);
            container.setTagCompound(tag);
        }
        return getCapacity(container);
    }

    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
        if (maxDrain < getCapacity(container)) {
            return null;
        }
        FluidStack fluidStack = getFluid(container);
        if (doDrain && fluidStack != null) {
            ItemStack empty = ItemCells.getCellByName("empty");
            if (empty != null) {
                container.setItemDamage(empty.getItemDamage());
                container.setTagCompound(empty.getTagCompound());
            } else {
                container.stackSize = 0;
            }
        }

        return fluidStack;
    }
}
